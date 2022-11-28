import time
from random import choice, randint

import pygame
import sys

import obstacle
from alien import Alien
from laser import Laser
from player import Player


# Clase que contiene el loop del juego y su configuración
class Game:
    def __init__(self):
        # Configuración del jugador
        player_sprite = Player((screen_width / 2, screen_height - 10), screen_width, 5)
        self.player = pygame.sprite.GroupSingle(player_sprite)

        # Configuración de salud y puntuación
        self.lives = 3
        self.live_surf = pygame.image.load('../graphics/player.png').convert_alpha()
        self.live_x_start_pos = screen_width - (self.live_surf.get_size()[0] * 2 + 20)
        self.score = 0
        self.font = pygame.font.Font('../font/Pixeled.ttf', 20)

        # Configuración de los obstáculos
        self.shape = obstacle.shape
        self.block_size = 6  # Tamaño de los obstáculos
        self.blocks = pygame.sprite.Group()
        self.obstacle_amount = 4  # Cantidad de obstáculos
        self.obstacle_x_positions = [num * (screen_width / self.obstacle_amount) for num in range(self.obstacle_amount)]
        self.create_multiple_obstacles(*self.obstacle_x_positions, x_start=screen_width / 15, y_start=480)

        # Configuración de los aliens
        self.aliens = pygame.sprite.Group()
        self.alien_lasers = pygame.sprite.Group()
        self.alien_setup(rows=3, cols=5)  # Filas y columnas de enemigos
        self.alien_direction = 1

        # Salida
        self.exit = False  # Variable que controla si el juego ha terminado

        # Configuración de audio
        music = pygame.mixer.Sound('../audio/music.wav')  # Dirección del archivo de audio
        music.set_volume(0.2)  # Volumen de la música
        music.play(loops=-1)
        self.laser_sound = pygame.mixer.Sound('../audio/laser.wav')  # Dirección del archivo de audio
        self.laser_sound.set_volume(0.1)  # Volumen de la música
        self.explosion_sound = pygame.mixer.Sound('../audio/explosion.wav')  # Dirección del archivo de audio
        self.explosion_sound.set_volume(0.75)  # Volumen de la música

    # Función que crea los obstáculos en el juego
    def create_obstacle(self, x_start, y_start, offset_x):
        for row_index, row in enumerate(self.shape):
            for col_index, col in enumerate(row):
                if col == 'x':
                    x = x_start + col_index * self.block_size + offset_x
                    y = y_start + row_index * self.block_size
                    block = obstacle.Block(self.block_size, (240, 80, 80), x, y)
                    self.blocks.add(block)

    # Función que crea múltiples obstáculos
    def create_multiple_obstacles(self, *offset, x_start, y_start):
        for offset_x in offset:
            self.create_obstacle(x_start, y_start, offset_x)

    # Función que configura los aliens
    def alien_setup(self, rows, cols, x_distance=60, y_distance=48, x_offset=70, y_offset=100):
        for row_index, row in enumerate(range(rows)):
            for col_index, col in enumerate(range(cols)):
                x = col_index * x_distance + x_offset
                y = row_index * y_distance + y_offset

                if row_index == 0:  # Dibuja aliens de tipo 'morado' en la línea 0
                    alien_sprite = Alien('purple', x, y)
                elif 1 <= row_index <= 1:  # Dibuja aliens de tipo 'rojo' en la línea 1
                    alien_sprite = Alien('red', x, y)
                else:  # Dibuja aliens de tipo 'verde' en la línea 2
                    alien_sprite = Alien('green', x, y)
                self.aliens.add(alien_sprite)

    # Función que controla la posición de los aliens para comprobar la colisión
    def alien_position_checker(self):
        all_aliens = self.aliens.sprites()
        for alien in all_aliens:
            if alien.rect.right >= screen_width:
                self.alien_direction = -1
                self.alien_move_down(2)
            elif alien.rect.left <= 0:
                self.alien_direction = 1
                self.alien_move_down(2)

    # Función que desplaza los aliens hacia abajo si han colisionado con el muro
    def alien_move_down(self, distance):
        if self.aliens:
            for alien in self.aliens.sprites():
                alien.rect.y += distance

    # Función que genera los disparos de los aliens
    def alien_shoot(self):
        if self.aliens.sprites():
            random_alien = choice(self.aliens.sprites())
            laser_sprite = Laser(random_alien.rect.center, 6, screen_height)
            self.alien_lasers.add(laser_sprite)
            self.laser_sound.play()

    # Función que comprueba las colisiones
    def collision_checks(self):

        # Disparos del jugador
        if self.player.sprite.lasers:
            for laser in self.player.sprite.lasers:
                # Colisión con obstáculos
                if pygame.sprite.spritecollide(laser, self.blocks, True):
                    laser.kill()

                # Colisión con aliens
                aliens_hit = pygame.sprite.spritecollide(laser, self.aliens, True)
                if aliens_hit:
                    for alien in aliens_hit:
                        self.score += alien.value
                    laser.kill()
                    self.explosion_sound.play()

        # Disparos de los aliens
        if self.alien_lasers:
            for laser in self.alien_lasers:
                # Colisión con obstáculos
                if pygame.sprite.spritecollide(laser, self.blocks, True):
                    laser.kill()
                # Colisión con el jugador
                if pygame.sprite.spritecollide(laser, self.player, False):
                    laser.kill()
                    self.lives -= 1  # Resta una vida en caso de colisión

        # Aliens
        if self.aliens:
            for alien in self.aliens:
                pygame.sprite.spritecollide(alien, self.blocks, True)
                # Comprueba que todos los aliens entran en la pantalla sin colisionar entre sí
                if pygame.sprite.spritecollide(alien, self.player, False):
                    pygame.quit()
                    sys.exit()

    # Función que muestra las vidas de nuestro jugador
    def display_lives(self):
        for live in range(self.lives - 1):
            x = self.live_x_start_pos + (live * (self.live_surf.get_size()[0] + 10))
            screen.blit(self.live_surf, (x, 8))

    # Función que muestra la puntuación de nuestro jugador
    def display_score(self):
        score_surf = self.font.render(f'score: {self.score}', False, 'white')
        score_rect = score_surf.get_rect(topleft=(10, -10))
        screen.blit(score_surf, score_rect)

    # Función que escribe un mensaje de victoria al ganar
    def victory_message(self):
        if not self.aliens.sprites():
            victory_surf = self.font.render('You won', False, 'white')
            victory_rect = victory_surf.get_rect(center=(screen_width / 2, screen_height / 2))
            screen.blit(victory_surf, victory_rect)
            crt.draw()
            pygame.display.update()
            self.exit = True

    # Función que escribe un mensaje de derrota al perder
    def defeat_message(self):
        if self.lives <= 0:
            defeat_surf = self.font.render('GAME OVER', True, 'white')
            defeat_rect = defeat_surf.get_rect(center=(screen_width / 2, screen_height / 2))
            screen.blit(defeat_surf, defeat_rect)
            crt.draw()
            pygame.display.update()
            self.exit = True

    # Función que cierra el juego cuando haya terminado
    def close_app(self):
        if self.exit:
            time.sleep(3)  # Espera 3 segundos antes de cerrar la aplicación
            pygame.quit()
            sys.exit()

    # Función run que invoca al resto de funciones
    def run(self):
        self.player.update()
        self.alien_lasers.update()
        self.aliens.update(self.alien_direction)
        self.alien_position_checker()
        self.collision_checks()
        self.player.sprite.lasers.draw(screen)
        self.player.draw(screen)
        self.blocks.draw(screen)
        self.aliens.draw(screen)
        self.alien_lasers.draw(screen)
        self.display_lives()
        self.display_score()
        self.victory_message()
        self.defeat_message()
        self.close_app()


# Clase que aplica el filtro de TV a nuestro juego
class CRT:
    def __init__(self):
        self.tv = pygame.image.load('../graphics/tv.png').convert_alpha()  # Dirección del filtro
        self.tv = pygame.transform.scale(self.tv, (screen_width, screen_height))

    # Función que crea el filtro de TV
    def create_crt_lines(self):
        line_height = 3
        line_amount = int(screen_height / line_height)
        for line in range(line_amount):
            y_pos = line * line_height
            pygame.draw.line(self.tv, 'black', (0, y_pos), (screen_width, y_pos), 1)

    # Función que dibuja el filtro de TV
    def draw(self):
        self.tv.set_alpha(randint(75, 90))
        self.create_crt_lines()
        screen.blit(self.tv, (0, 0))


if __name__ == '__main__':
    # Iniciamos pygame y configuramos la ventana
    pygame.init()
    screen_width = 600  # Ancho de la ventana
    screen_height = 600  # Alto de la ventana
    screen = pygame.display.set_mode((screen_width, screen_height))
    clock = pygame.time.Clock()
    game = Game()  # Crea un objeto juego
    crt = CRT()  # Crea un objeto CRT para dibujar el filtro

    ALIENLASER = pygame.USEREVENT + 1
    pygame.time.set_timer(ALIENLASER, 500)  # Tiempo entre cada disparo de los enemigos

    # Bucle del juego
    while True:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                sys.exit()
            if event.type == ALIENLASER:
                game.alien_shoot()

        screen.fill((30, 30, 30))
        game.run()
        crt.draw()

        pygame.display.flip()
        clock.tick(60)
