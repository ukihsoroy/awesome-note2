import card_system.cards_tools as tools


title_list = ["退出系统", "新建名片", "显示全部", "查询名片"]

while True:
    # 显示功能菜单
    tools.show_menu()
    action_str = input("请选择希望执行的操作：")

    # 1, 2, 3针对名片的操作
    if action_str in ["1", "2", "3"]:
        print("您选择的操作是[%s]" % title_list[int(action_str)])
        if action_str == "1":
            tools.new_card()
        elif action_str == "2":
            tools.show_all()
        else:
            tools.search_card()
    # 0 退出系统
    elif action_str == "0":
        print("您选择的操作是[%s]" % title_list[int(action_str)])
        # 如果在开发程序时，不希望立刻编写内部的代码
        # 可以使用 pass 关键字，表示一个占位符，可以保证程序代码结构正确！
        # 程序运行时，pass 关键字不会执行任何操作
        break

    # 输入内容输入错误，提示用户
    else:
        print("您输入的不正确请重新选择")
    # 其他内容输入错误，需要提示用户