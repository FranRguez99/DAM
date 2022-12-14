import pygame


# Clase que crea los disparos de nuestro juego
class Laser(pygame.sprite.Sprite):
    def __init__(self, pos, speed, screen_height):
        super().__init__()
        self.image = pygame.image.load('../graphics/laser.png').convert_alpha()  # Sprite del disparo
        self.rect = self.image.get_rect(center=pos)  # Dimensiones del objeto
        self.speed = speed  # Velocidad de la bala
        self.height_y_constraint = screen_height  # Posición de la bala

    # Función que destruye la bala
    def destroy(self):
        if self.rect.y <= -50 or self.rect.y >= self.height_y_constraint + 50:
            self.kill()

    # Función que modifica la posición de la bala
    def update(self):
        self.rect.y += self.speed
        self.destroy()
