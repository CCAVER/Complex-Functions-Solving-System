import sys
import os
sys.path.append("c:\\users\\hy\\appdata\\local\\programs\\python\\python311\\lib\\site-packages")
import pymysql
import pymysql.cursors
import traceback
import seaborn as sns
import numpy as np
import matplotlib.pyplot as plt
from matplotlib.backends.backend_agg import FigureCanvasAgg
import PIL.Image as Image
from io import BytesIO
import argparse
from sympy import *
from sympy import Symbol, diff, integrate, sin, cos, Function
from sympy.utilities.lambdify import lambdify, implemented_function
from sympy.abc import x
import numpy as np
#
import matplotlib.pyplot as plt
from matplotlib.widgets import Slider, Button, RadioButtons
#
fig, ax = plt.subplots(figsize=(9, 2))
#
#
def gen():
    plt.xticks([])
    plt.yticks([])
    ax.spines['top'].set_visible(False)
    ax.spines['right'].set_visible(False)
    ax.spines['bottom'].set_visible(False)
    ax.spines['left'].set_visible(False)
    plt.text(0.5,
             0.5,
             #务必保持下行在第36行
             r'$\mathrm{f}(x) = \sin{\left({{5} x}\right)} + {7}$'
             ,ha='center'
             ,va='center'
             , fontsize=20)
    try:
        # 将plt转化为numpy数据
        #rcParams["mathtext.fontset"]
        canvas = FigureCanvasAgg(plt.gcf())
       
        canvas.draw()
        # 获取图像尺寸
        w, h = canvas.get_width_height()
        # 解码string 得到argb图像
        buf = np.fromstring(canvas.tostring_argb(), dtype=np.uint8)
        buf.shape = (w, h, 4)
        # 转 RGBA
        buf = np.roll(buf, 3, axis=2)
        # 得到 Image RGBA图像对象 (生成Image对象)
        image = Image.frombytes("RGBA", (w, h), buf.tostring())
       
        # 创建一个空的Bytes对象
        img_byte = BytesIO()
        #转存PNG      
        image.save(img_byte, format='PNG')
        # 保存的图片字节流
        binary_content = img_byte.getvalue()
        
        print('生成成功')
        return binary_content
    except Exception as e:
        print(e)
        print('生成失败')
        # sys.exit(1)
        return
def write_pic2mysql(name,config,uid):
    """
    读取图片写入数据库
    :param name: 读取的图片的名字
    :param config: 数据库连接配置信息
    :param matha:用户提供的函数
    :param uid:使用用户的id
    :param rowid:图片的id
    :return: None
    """
    #filename = path.split('/')[-1]
    
    path="D:\\学习系列\\毕业论文-定档\\"+uid+"\\"
    #sys.path.append("Desktop\\论文相关\\"+uid)
    
    
    filename=name+'.png'
    
    try:        
        img=gen()
    except Exception as e:
        print(e)
        print('读取失败')
        # sys.exit(1)
        return
    try:
        conn = pymysql.connect(host=config['host'],
                               port=config['port'],
                               user=config['user'],
                               passwd=config['password'],
                               db=config['db'],
                               charset='utf8',
                               use_unicode=True)
        cursor = conn.cursor()
        sql = "update latex set latex.img=%s where uid='{0}'".format(uid)
        cursor.execute(sql, img)
        conn.commit()
        cursor.close()
        conn.close()
        print('写入 {} 成功'.format(filename))
    except Exception as e:
        print(e)
        print('写入失败')
        
if __name__ == '__main__':
    #rowid=int(sys.argv[1])#id
    #fname=str(sys.argv[2])#保存的文件名
    #uid=str(sys.argv[3])#用户id
    #--------↓防止在命令行中找不到包--------
    curPath = os.path.abspath(os.path.dirname(__file__))
    rootPath = os.path.split(curPath)[0]
    sys.path.append(rootPath)
    
    
    fname='DW'
    rowid=1
    uid=1
    parser = argparse.ArgumentParser(description='manual to this script')
    parser.add_argument('--uid', type=str, default = '19')
    parser.add_argument('--fname', type=str, default = 'empty')
    args = parser.parse_args()
    my_config = {'host': 'localhost', 'port': 3306, 'user': 'root',
                 'password': '123456', 'db': 'finalwork'}
    #import io3 as i
    #i.changetext(4,args.matha,37)#4为fucs.py第5行,37为picGen.py第38行
    qal0=args.fname
    write_pic2mysql(args.fname,my_config,args.uid)
    #python picGen.py --matha cos(x)+1 --rowid 23 --fname 1 --uid 19 --mini '-10' --maxi '10'        