#该文件在\pyConn\src\main\java\com\example\pyConn\service\impl\pyServImpl.java中进行配置
import pymysql.cursors
import os
import traceback
import sys
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
import matplotlib.pyplot as plt
from matplotlib.widgets import Slider, Button, RadioButtons

#str(sys.argv[1])    

def sinplot(matha,mini,maxi,qal,wei,lent):
    #x=Symbol('x') 
    #x=np.linspace(0,20,150)
    #plot(sympify(matha),(x, -pi, pi))
    #plt.plot(x,np.cos(x+0.8)*(9-2))
    #sns.set_style("whitegrid")
    
    evalfunc = lambdify((x), sympify(matha), modules=['numpy'])


    #axis_color = 'lightgoldenrodyellow'
    fig = plt.figure()
    #ax = fig.add_subplot(1,1,1)
    fig.subplots_adjust(left=0.25, bottom=0.25)
    t = np.arange(mini, maxi, qal) 
    
    #plt.gcf().set_facecolor(np.ones(3)* 240 / 255)
    plt.grid(c='g')
    #plt.rcParams['figure.figsize'] = (wei, lent) # 单位是inches
    plt.figure(figsize=(lent,wei))
    plt.plot(t, evalfunc(t), linewidth=2, color='red')

    try:
        # 将plt转化为numpy数据
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
    
def write_pic2mysql(name, config,matha,uid,rowid,mini,maxi,qal,wei,lent):
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
    filename=name+'.png'
    evalfunc = lambdify((x), sympify(mini), modules=['numpy'])
    mini=evalfunc(mini);
    evalfunc = lambdify((x), sympify(maxi), modules=['numpy'])
    maxi=evalfunc(maxi);
    try:
        #with open(path, 'rb') as f:
            #img = f.read()
            #img=sinplot()
        img=sinplot(matha,mini,maxi,qal,wei,lent)
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

        sql = "update images,imginf set images.data=%s,images.name='{0}',images.uid='{1}',imginf.matha='{3}',imginf.mini='{4}',imginf.maxi='{5}',imginf.qal='{6}',imginf.wei='{7}',imginf.lent='{8}' where images.id={2} and imginf.ImgId={2};".format(filename,uid,rowid,matha,mini,maxi,qal,wei,lent)
        cursor.execute(sql, img)
        conn.commit()
        cursor.close()
        conn.close()
        #print('写入 {} 成功'.format(filename))

    except Exception as e:
        print(e)
        print('写入失败')


def read_mysql2pic(path, filename, config):
    """
    从数据库中读取图片
    :param path: 保存的图片的路径
    :param filename:从数据库读取的ID
    :param config: 数据库连接配置信息
    :return: None
    """
    try:
        conn = pymysql.connect(host=config['host'],
                               port=config['port'],
                               user=config['user'],
                               passwd=config['password'],
                               db=config['db'],
                               charset='utf8',
                               use_unicode=True)
        cursor = conn.cursor()
        cursor.execute("select data from images where id = '{}'".format(filename))
        res = cursor.fetchone()[0]
        with open(path, 'wb') as f:
            f.write(res)
        print('从数据库中读取 {} 成功'.format(filename))
    except Exception as e:
        print(e)
        print('读取数据库中的图片失败')


if __name__ == '__main__':
    #rowid=int(sys.argv[1])#id
    #fname=str(sys.argv[2])#保存的文件名
    #uid=str(sys.argv[3])#用户id
    fname='DW'
    rowid=1
    uid=1

    parser = argparse.ArgumentParser(description='manual to this script')
    parser.add_argument('--matha', type=str, default = 'cos(x)')
    parser.add_argument('--uid', type=str, default = '1')
    parser.add_argument('--fname', type=str, default = 'empty')
    parser.add_argument('--rowid', type=str, default = '1')
    parser.add_argument('--mini', type=str, default = '-10.0')
    parser.add_argument('--maxi', type=str, default = '10.0')
    parser.add_argument('--qal', type=float, default = 0.001)#线条密度
    parser.add_argument('--hei', type=float, default = 10.0)#宽
    parser.add_argument('--lent', type=float, default = 10.0)#长
    args = parser.parse_args()
    #matha=str(args.matha)
    #matha='cos(x+8)*(9-2)'
    my_config = {'host': 'localhost', 'port': 3306, 'user': 'root',
                 'password': '123456', 'db': 'finalwork'}
    write_pic2mysql(args.fname,my_config,args.matha,args.uid,args.rowid,args.mini,args.maxi,args.qal,args.hei,args.lent)
    #write_pic2mysql('DW',my_config,args.matha,'1',1)
  	#print(' 写入后再读取 ')
    #read_mysql2pic('test.png', args.rowid, my_config)