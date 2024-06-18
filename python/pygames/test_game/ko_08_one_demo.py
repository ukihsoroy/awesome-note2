#   _*_ coding:utf-8 _*_
from random import randint
import pygame
from pygame.locals import *
from sys import exit

__author__ = 'admin'

'''
    童年的回忆：掌上游戏机之赛车
'''

pygame.init()
#   设置屏幕宽度
SCREEN_SIZE = (320, 400)
#   颜色
BLACK = (0, 0, 0)
WHITE = (250, 250, 250)
BLUE = (0, 255, 255)
RED = (255, 0, 0)
GREEN = (0, 255, 0)
ORANGE = (255, 128, 64)
DARK_GREEN = (0, 64, 0)
#   调试网格中单元格的长宽
cell_width = 20
cell_height = 20

#   初始化游戏状态：1/游戏开始界面 2/游戏进行中 3/游戏结束
status = 1
#   阻挡对象的列表
blocks = []
#   斑马线对象的列表
zebras = []
#   初始化得分
score = 0

speed_para = 4
clock = pygame.time.Clock()

screen = pygame.display.set_mode(SCREEN_SIZE, 0, 32)


class Block():
    def __init__(self):
        #   随机生成通道位置的参照坐标
        self.refer_x = randint(0, SCREEN_SIZE[0] + 1)
        #   障碍物起始纵坐标
        self.block_y = 0
        #   随机生成通道的大小
        self.passway_width = randint(4, 10) * cell_width
        #   随机生成障碍物的间距,可以依据这个控制同屏出现的最大障碍物数
        self.block_spacing = randint(10, 10) * cell_height
        #   block的高度，可以设计成随机高度
        self.block_height = randint(5, 20) / 10 * cell_height

        #   初始化
        self.block_right_pos = (0, 0)
        self.block_left_pos = (0, 0)
        self.block_color = ORANGE

        #   判定碰撞时需要使用block的底部纵坐标
        self.block_bottom_y = self.block_y + self.block_height

    #   障碍物移动情况
    def block_move_down(self):
        self.block_y += 1 * speed_para

    #   画出本次Block()对象
    def draw(self):
        #   通道随机出的参照坐标为左侧边缘
        if self.refer_x == 0:
            self.block_right_pos = (self.passway_width, self.block_y)
            pygame.draw.rect(screen, self.block_color,
                             pygame.Rect(self.block_right_pos,
                                         (SCREEN_SIZE[0] - self.passway_width, self.block_height)))
        # 通道随机出的参照坐标为右侧边缘
        elif self.refer_x == SCREEN_SIZE[0]:
            self.block_left_pos = (0, self.block_y)
            pygame.draw.rect(screen, self.block_color,
                             pygame.Rect(self.block_left_pos, (SCREEN_SIZE[0] - self.passway_width, self.block_height)))
        # 通道随机出的参照坐标距离右侧边缘间不足以满足你设定的self.passway_width通道宽度
        elif self.refer_x + self.passway_width > 320:
            self.block_left_pos = (0, self.block_y)
            self.block_right_pos = (self.refer_x, self.block_y)
            pygame.draw.rect(screen, self.block_color,
                             pygame.Rect(self.block_left_pos, (self.refer_x - self.passway_width, self.block_height)))
            pygame.draw.rect(screen, self.block_color,
                             pygame.Rect(self.block_right_pos, (SCREEN_SIZE[0] - self.refer_x, self.block_height)))
        else:
            self.block_left_pos = (0, self.block_y)
            self.block_right_pos = (self.refer_x + self.passway_width, self.block_y)
            pygame.draw.rect(screen, self.block_color,
                             pygame.Rect(self.block_left_pos, (self.refer_x, self.block_height)))
            pygame.draw.rect(screen, self.block_color,
                             pygame.Rect(self.block_right_pos,
                                         (SCREEN_SIZE[0] - self.block_right_pos[0], self.block_height)))

    # 更新障碍物的位置以及生成新的障碍物对象
    def update(self):
        global score, status
        self.block_move_down()
        if self.block_y == self.block_spacing:
            blocks.append(Block())
        if self.block_y > SCREEN_SIZE[1]:
            del blocks[0]
            score += 1
            print(score)
        # 判定碰撞时需要使用block的底部纵坐标
        block_bottom_y = self.block_y + self.block_height
        #   获取car的几个属性
        car_pos_y = car.car_pos_y
        car_pos_x = car.car_pos_x
        car_width = car.car_width
        #   如果block的底部在car的高度内，需要判断碰撞
        if car_pos_y <= block_bottom_y <= car_pos_y + 3 * cell_height:
            #   通道随机出的参照坐标为左侧边缘
            if self.refer_x == 0:
                #   如果car的躯壳在通道内，判定为不碰撞
                if self.refer_x <= car_pos_x <= self.refer_x + self.passway_width - car_width:
                    pass
                #   car的躯壳不在通道内
                else:
                    status = 3
            # 通道随机出的参照坐标为右侧边缘
            elif self.refer_x == SCREEN_SIZE[0]:
                #   如果car的躯壳在通道内，判定为不碰撞
                if SCREEN_SIZE[0] - self.passway_width <= car_pos_x <= SCREEN_SIZE[0] - car_width:
                    pass
                #   car的躯壳不在通道内
                else:
                    status = 3
            # 通道随机出的参照坐标距离右侧边缘间不足以满足你设定的self.passway_width通道宽度
            elif self.refer_x + self.passway_width > 320:
                #   如果car的躯壳在通道内，判定为不碰撞
                if self.refer_x - self.passway_width <= car_pos_x <= self.refer_x - car_width:
                    pass
                #   car的躯壳不在通道内
                else:
                    status = 3
            else:
                #   如果car的躯壳在通道内，判定为不碰撞
                if self.refer_x <= car_pos_x <= self.refer_x + self.passway_width - car_width:
                    pass
                #   car的躯壳不在通道内
                else:
                    status = 3


blocks.append(Block())


class Car():
    def __init__(self):
        #   定义car的宽度及高度
        self.car_width = cell_width * 3
        self.car_height = cell_height * 4
        #   获取car的左上角定点坐标(这里的car区域实际上是一个矩形)
        self.car_pos_x = SCREEN_SIZE[0] / 2 - (cell_width + cell_width / 2)
        self.car_pos_y = SCREEN_SIZE[1] - cell_height * 4
        #   设置car的移动速度
        self.car_speed = 5
        self.car_color = GREEN

    #   car的左移处理
    def car_move_left(self):
        self.car_pos_x -= 2 * self.car_speed
        self.car_pos_x = max(0, self.car_pos_x)

    #   car的右移处理
    def car_move_right(self):
        self.car_pos_x += 2 * self.car_speed
        self.car_pos_x = min(SCREEN_SIZE[0] - cell_width * 3, self.car_pos_x)

    #   更新car中每个需要染色部分的坐标，以方便在car矩形图中染色出car的形状
    def update_car_pos(self):
        car_point_1 = (self.car_pos_x + cell_width, self.car_pos_y)
        car_point_2 = (self.car_pos_x, self.car_pos_y + cell_height)
        car_point_3 = (self.car_pos_x + cell_width, self.car_pos_y + cell_height)
        car_point_4 = (self.car_pos_x + cell_width * 2, self.car_pos_y + cell_height)
        car_point_5 = (self.car_pos_x + cell_width, self.car_pos_y + cell_height * 2)
        car_point_6 = (self.car_pos_x, self.car_pos_y + cell_height * 3)
        car_point_7 = (self.car_pos_x + cell_width, self.car_pos_y + cell_height * 3)
        car_point_8 = (self.car_pos_x + cell_width * 2, self.car_pos_y + cell_height * 3)
        car_points = [car_point_1, car_point_2, car_point_3, car_point_4, car_point_5, car_point_6, car_point_7,
                      car_point_8]
        return car_points

    #   画出car
    def draw(self, car_points):
        for car_point in car_points:
            pygame.draw.rect(screen, self.car_color, pygame.Rect(car_point, (cell_width, cell_height)))


car = Car()


#   斑马线
class Zebra:
    def __init__(self):
        #   定义一个斑马线对象的高度及宽度
        self.zebra_rect_height = 5 * cell_height
        self.zebra_rect_width = 3 * cell_width
        #   初始化斑马线区域左上角坐标
        self.zebra_pos_x, self.zebra_pos_y = (0, 0)
        #   获取斑马线中间的矩形块左上角坐标
        self.zebra_rect_x = SCREEN_SIZE[0] / 2 - cell_width / 2
        self.zebra_rect_y = self.zebra_pos_y + (cell_height + cell_height / 2)

    def draw(self):
        #   斑马线左侧虚线的横坐标
        left_line_x = SCREEN_SIZE[0] / 2 - (cell_width + cell_width / 2)
        #   斑马线右侧虚线的横坐标
        right_line_x = SCREEN_SIZE[0] / 2 + (cell_width + cell_width / 2)
        #   一个斑马线对象的两侧虚线高度
        line_height = self.zebra_pos_y + 5 * cell_height
        #   画出一个斑马现对象的两侧虚线
        for i in range(self.zebra_pos_y, line_height + 1, cell_height * 2):
            pygame.draw.line(screen, WHITE, (left_line_x, i), (left_line_x, i + cell_height))
            pygame.draw.line(screen, WHITE, (right_line_x, i), (right_line_x, i + cell_height))
        #   斑马线中间矩形块的宽度及高度
        zebra_rect_width = cell_width
        zebra_rect_height = cell_height * 2
        #   画出斑马线对象的中间矩形块
        pygame.draw.rect(screen, WHITE,
                         pygame.Rect((self.zebra_rect_x, self.zebra_rect_y), (zebra_rect_width, zebra_rect_height)))

    def update(self):
        self.zebra_pos_y += 1 * speed_para
        self.zebra_rect_y = self.zebra_pos_y + (cell_height + cell_height / 2)
        if self.zebra_pos_y == self.zebra_rect_height:
            zebras.append(Zebra())
        if self.zebra_pos_y > SCREEN_SIZE[1]:
            del zebras[0]


zebras.append(Zebra())


# 退出检测函数
def checkForOut():
    global status, blocks, score, car, block
    for event in pygame.event.get():
        #   点击×
        if event.type == 12:
            exit()
        if event.type == 2:
            #   按键为Esc
            if event.key == 27:
                exit()
            elif event.key == 32:
                if status == 1:
                    status = 2
                elif status == 3:
                    score = 0
                    blocks = []
                    car = Car()
                    blocks.append(Block())
                    status = 2

#   调试用网格
def debug_grid(bool):
    grid_color = WHITE
    if bool:
        #   调试用网格
        for i in range(cell_width, 320, cell_width):
            pygame.draw.line(screen, grid_color, (i, 0), (i, 400))
        for j in range(cell_height, 400, cell_height):
            pygame.draw.line(screen, grid_color, (0, j), (320, j))

#   游戏开始菜单的设计
def start_menu():
    font = pygame.font.SysFont("arial", 30)
    text_surWarn = font.render(u"START", True, BLUE)
    warn_width = text_surWarn.get_width()
    warn_height = text_surWarn.get_height()
    pos_warn = (SCREEN_SIZE[0] / 2 - warn_width / 2, SCREEN_SIZE[1] / 2 - warn_height / 2)
    screen.blit(text_surWarn, pos_warn)

#   游戏结束菜单的设计
def end_menu():
    font_score = pygame.font.SysFont("arial", 30)
    font_warn = pygame.font.SysFont("arial", 30, 5)
    text_surScore = font_score.render("Score:%d" % score, True, RED)
    text_surWarn = font_warn.render(u"RESTART", True, RED)

    winner_width = text_surScore.get_width()
    winner_height = text_surScore.get_height()
    warn_width = text_surWarn.get_width()
    warn_height = text_surWarn.get_height()

    rect_width = max(winner_width, warn_width)
    rect_height = winner_height + warn_height
    rect_y = SCREEN_SIZE[1] / 2 - rect_height / 2
    rect_x = SCREEN_SIZE[0] / 2 - rect_width / 2
    pygame.draw.rect(screen, GREEN, pygame.Rect((rect_x, rect_y), (rect_width, rect_height)))

    #   显示居中处理
    winner_x = SCREEN_SIZE[0] / 2 - winner_width / 2
    winner_y = rect_y
    screen.blit(text_surScore, (winner_x, winner_y))

    #   显示居中处理
    pos_warn_x = SCREEN_SIZE[0] / 2 - warn_width / 2
    pos_warn_y = rect_y + winner_height
    screen.blit(text_surWarn, (pos_warn_x, pos_warn_y))


if __name__ == '__main__':
    while True:
        checkForOut()

        if status == 1:
            start_menu()
        if status == 2:
            #   初始化car
            car_points = car.update_car_pos()

            #   捕获按键
            key_pressed = pygame.key.get_pressed()
            #   如果按下LEFT键，执行挡板左移
            if key_pressed[276]:
                car.car_move_left()
                car_points = car.update_car_pos()
            # 如果按下RIGHT键，执行挡板右移
            elif key_pressed[275]:
                car.car_move_right()
                car_points = car.update_car_pos()

            #   填充颜色为黑色，刷新界面
            screen.fill(BLACK)

            for zebra in zebras:
                zebra.update()
                zebra.draw()

            #   分别显示blocks列表中的每一个bloc
            for block in blocks:
                block.update()
                block.draw()

            #   画出car
            car.draw(car_points)

            #   开启网格调试
            debug_grid(False)
        if status == 3:
            end_menu()
        #   延迟界面刷新
        clock.tick(60)
        pygame.display.update()


