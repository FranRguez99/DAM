import pygame
from laser import Laser


# Clase que crea al personaje jugable en nuestro juego
class Player(pygame.sprite.Sprite):
    def __init__(self, pos, constraint, speed):
        super().__init__()
        self.image = pygame.image.load('../graphics/player.png').convert_alpha()  # Dirección del sprite
        self.rect = self.image.get_rect(midbottom=pos)  # Tamaño del objeto
        self.speed = speed  # Velocidad del jugador
        self.max_x_constraint = constraint
        self.ready = True
        self.laser_time = 0
        self.laser_cooldown = 300  # Tiempo de espera entre disparos

        self.lasers = pygame.sprite.Group()

        self.laser_sound = pygame.mixer.Sound('../audio/laser.wav')  # Sonido de los disparos
        self.laser_sound.set_volume(0.1)

    # Función que mueve al jugador cuando se pulsan las teclas de izquierda y derecha
    def get_input(self):
        keys = pygame.key.get_pressed()

        if keys[pygame.K_RIGHT]:
            self.rect.x += self.speed
        elif keys[pygame.K_LEFT]:
            self.rect.x -= self.speed

        if keys[pygame.K_SPACE] and self.ready:
            self.shoot_laser()
            self.ready = False
            self.laser_time = pygame.time.get_ticks()
            self.laser_sound.play()

    # Función que recarga el disparo del jugador
    def recharge(self):
        if not self.ready:
            current_time = pygame.time.get_ticks()
            if current_time - self.laser_time >= self.laser_cooldown:
                self.ready = True

    # Función que mantiene al jugador en la ventana de juego
    def constraint(self):
        if self.rect.left <= 0:
            self.rect.left = 0
        if self.rect.right >= self.max_x_constraint:
            self.rect.right = self.max_x_constraint

    # Función que genera los disparos del jugador
    def shoot_laser(self):
        self.lasers.add(Laser(self.rect.center, -8, self.rect.bottom))

    # Función que actualiza la posición del jugador
    def update(self):
        self.get_input()
        self.constraint()
        self.recharge()
        self.lasers.update()
