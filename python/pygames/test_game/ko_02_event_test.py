import pygame
from pygame.locals import *
from sys import exit

pygame.init()
SCREEN_SIZE = (640, 480)
screen = pygame.display.set_mode(SCREEN_SIZE, 0, 32)

font = pygame.font.SysFont("arial", 16);
font_height = font.get_linesize()
event_text = []

while True:

    # 获得时间的名称
    event = pygame.event.wait()
    event_text.append(str(event))

    if event.type == QUIT:
        exit()

    screen.fill((255, 255, 255))

    # 找一个合适的起笔位置，最下面开始但是要留一行的空
    y = SCREEN_SIZE[1] - font_height
    for text in reversed(event_text):
        screen.blit(font.render(text, True, (0, 0, 0)), (0, y))
        # 以后会讲
        y -= font_height
        # 把笔提一行

    pygame.display.update()