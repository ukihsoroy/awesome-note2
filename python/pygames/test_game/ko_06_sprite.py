import pygame
from pygame.locals import *
from sys import exit

background_image_filename = 'D:/code/3000-Times/pygames/test_game/resource/background.jpg'
sprite_image_filename = 'D:/code/3000-Times/pygames/test_game/resource/mouse.png'

pygame.init()

screen = pygame.display.set_mode((640, 480), 0, 32)

background = pygame.image.load(background_image_filename).convert()
sprite = pygame.image.load(sprite_image_filename)

# sprite的起始x坐标
x = 0.

while True:

    for event in pygame.event.get():
        if event.type == QUIT:
            exit()

    screen.blit(background, (0, 0))
    screen.blit(sprite, (x, 100))
    x += 1.  # 如果你的机器性能太好以至于看不清，可以把这个数字改小一些

    # 如果移动出屏幕了，就搬到开始位置继续
    if x > 640.:
        x = 0.

    pygame.display.update()