import pygame
import random

# 定义一些常量
SCREEN_WIDTH = 800
SCREEN_HEIGHT = 600
BRICK_WIDTH = 80
BRICK_HEIGHT = 30
PADDLE_WIDTH = 150
PADDLE_HEIGHT = 20
BALL_RADIUS = 10
BRICK_ROWS = 5
BRICK_COLS = 10
BRICK_EFFECT_PROBABILITY = 0.2
BRICK_EFFECT_TYPES = ['speed_up', 'speed_down', 'multi_ball', 'big_ball']
PADDLE_SPEED = 10
BALL_SPEED = 5

# 定义颜色
WHITE = (255, 255, 255)
RED = (255, 0, 0)
GREEN = (0, 255, 0)
BLUE = (0, 0, 255)
YELLOW = (255, 255, 0)

# 初始化Pygame
pygame.init()

# 创建游戏窗口
screen = pygame.display.set_mode((SCREEN_WIDTH, SCREEN_HEIGHT))
pygame.display.set_caption("Brick Breaker")

# 定义球类
class Ball:
    def __init__(self):
        self.x = SCREEN_WIDTH // 2
        self.y = SCREEN_HEIGHT // 2
        self.dx = BALL_SPEED
        self.dy = BALL_SPEED
        self.radius = BALL_RADIUS
        self.color = WHITE

    def draw(self):
        pygame.draw.circle(screen, self.color, (self.x, self.y), self.radius)

    def move(self):
        self.x += self.dx
        self.y += self.dy

        # 球碰到边界时反弹
        if self.x - self.radius <= 0 or self.x + self.radius >= SCREEN_WIDTH:
            self.dx *= -1
        if self.y - self.radius <= 0:
            self.dy *= -1

    def reset(self):
        self.x = SCREEN_WIDTH // 2
        self.y = SCREEN_HEIGHT // 2
        self.dx = BALL_SPEED
        self.dy = BALL_SPEED

# 定义挡板类
class Paddle:
    def __init__(self):
        self.x = SCREEN_WIDTH // 2 - PADDLE_WIDTH // 2
        self.y = SCREEN_HEIGHT - 50
        self.width = PADDLE_WIDTH
        self.height = PADDLE_HEIGHT
        self.color = GREEN

    def draw(self):
        pygame.draw.rect(screen, self.color, (self.x, self.y, self.width, self.height))

    def move(self, direction):
        if direction == "left":
            self.x -= PADDLE_SPEED
        elif direction == "right":
            self.x += PADDLE_SPEED

        # 防止挡板超出边界
        if self.x <= 0:
            self.x = 0
        elif self.x + self.width >= SCREEN_WIDTH:
            self.x = SCREEN_WIDTH - self.width

# 定义砖块类
class Brick:
    def __init__(self, x, y):
        self.x = x
        self.y = y
        self.width = BRICK_WIDTH
        self.height = BRICK_HEIGHT
        self.color = RED
        self.effect = None

    def draw(self):
        pygame.draw.rect(screen, self.color, (self.x, self.y, self.width, self.height))

# 初始化球、挡板和砖块
ball = Ball()
paddle = Paddle()
bricks = []
for i in range(BRICK_ROWS):
    for j in range(BRICK_COLS):
        brick = Brick(j * (BRICK_WIDTH + 2), i * (BRICK_HEIGHT + 2) + 50)
        # 随机选择砖块是否带有特效
        if random.random() < BRICK_EFFECT_PROBABILITY:
            brick.effect = random.choice(BRICK_EFFECT_TYPES)
        bricks.append(brick)

# 游戏主循环
running = True
while running:
    screen.fill(BLACK)

    # 处理事件
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False

    # 移动挡板
    keys = pygame.key.get_pressed()
    if keys[pygame.K_LEFT]:
        paddle.move("left")
    if keys[pygame.K_RIGHT]:
        paddle.move("right")

    # 移动球
    ball.move()

    # 球与挡板的碰撞检测
    if ball.y + ball.radius >= paddle.y and ball.x >= paddle.x and ball.x <= paddle.x + paddle.width:
        ball.dy *= -1

    # 球与砖块的碰撞检测
    for brick in bricks:
        if ball.y - ball.radius <= brick.y + brick.height and ball.y + ball.radius >= brick.y and ball.x >= brick.x and ball.x <= brick.x + brick.width:
            ball.dy *= -1
            bricks.remove(brick)

            # 处理砖块特效
            if brick.effect:
                if brick.effect == 'speed_up':
                    ball.dx *= 1.2
                elif brick.effect == 'speed_down':
                    ball.dx *= 0.8
                elif brick.effect == 'multi_ball':
                    # 在当前球位置生成两个新球
                    pass  # 添加多球的实现
                elif brick.effect == 'big_ball':
                    ball.radius *= 2

    # 绘制球、挡板和砖块
    ball.draw()
    paddle.draw()
    for brick in bricks:
        brick.draw()

    pygame.display.update()
