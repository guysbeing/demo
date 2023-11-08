from pynput.keyboard import Controller
import time
from pynput.keyboard import Key

keyboard = Controller()

def press_ctrl_v():
    keyboard.press(Key.ctrl)
    keyboard.press('v')
    keyboard.release('v')
    keyboard.release(Key.ctrl)

def press_alt_s():
    keyboard.press(Key.alt)
    keyboard.press('s')
    keyboard.release('s')
    keyboard.release(Key.alt)

def press_enter():
    keyboard.press(Key.enter)
    keyboard.release(Key.enter)

def type_and_send(text):
    keyboard.type(text)


def keyboard_loop_enter(text, num_iterations, sleep): 
    time.sleep(2) # 等待2秒以聚焦到输入框
    for _ in range(num_iterations):
        type_and_send(text)
        press_enter()
        time.sleep(sleep)  # 可选的延迟

def keyboard_loop_alt_s(text, num_iterations, sleep): 
    time.sleep(2) # 等待2秒以聚焦到输入框
    for _ in range(num_iterations):
        type_and_send(text)
        press_alt_s()
        time.sleep(sleep)  # 可选的延迟

def cv_alt_s(num_iterations, sleep): 
    time.sleep(2) # 等待2秒以聚焦到输入框
    for _ in range(num_iterations):
        press_ctrl_v()
        press_alt_s()
        time.sleep(sleep)  # 可选的延迟

def cv_enter(num_iterations, sleep): 
    time.sleep(2) # 等待2秒以聚焦到输入框
    for _ in range(num_iterations):
        press_ctrl_v()
        press_enter()
        time.sleep(sleep)  # 可选的延迟

# 使用示例 - 使用Enter键发送文本消息（例如，微信）
# keyboard_loop_enter("Hello, World!", 20, 0.1)

# 使用示例 - 使用Alt+S键发送文本消息（例如，QQ）
# keyboard_loop_alt_s("Hello, World!", 20, 0.1)

# 使用示例 - 使用Alt+S键发送QQ表情
# keyboard_loop_alt_s("/xyx", 20, 0.1)

#提前粘贴好内容 推荐♥♥♥♥♥ 可以发送任何在粘贴板上的内容
# cv_enter(20,0.1)
# cv_alt_s(20,0.1)

cv_enter(5,0.1)