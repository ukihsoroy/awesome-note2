class Gun:

    def __init__(self, model):
        self.model = model
        self.bullet_count = 0

    def add_bullet(self, count):
        self.bullet_count = count

    def shoot(self):
        for i in range(0, self.bullet_count):
            print("开枪%d" % (i + 1))


class Soldier:

    def __init__(self):
        self.name = None
        self.gun = None

    def fire(self):

        if self.gun is None:
            print("还没有枪")

        self.gun.add_bullet(50)

        self.gun.shoot()


gun = Gun("98k")
soldier = Soldier()

soldier.gun = gun
soldier.name = "qiang ge"

soldier.fire()