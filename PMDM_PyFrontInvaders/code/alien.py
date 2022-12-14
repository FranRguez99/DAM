import pygame


# Clase alien que crea los enemigos de nuestro juego
class Alien(pygame.sprite.Sprite):
    def __init__(self, color, x, y):
        super().__init__()
        file_path = '../graphics/' + color + '.png'  # Dirección del sprite
        self.image = pygame.image.load(file_path).convert_alpha()  # Carga del sprite
        self.rect = self.image.get_rect(topleft=(x, y))  # Dimensiones del objeto

        if color == 'green':  # Puntos que da cada alien en el juego al matarlo
            self.value = 100
        elif color == 'red':
            self.value = 200
        else:
            self.value = 300

    # Función que modifica la posición del alien
    def update(self, direction):
        self.rect.x += direction
