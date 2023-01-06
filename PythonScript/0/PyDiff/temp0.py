import sympy as sp
import numpy as np
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
def sinplot(matha,mini,maxi,qal,wei,lent):
        
    x= sp.symbols('x')
    z = sp.sin(2*sp.pi*x**2)
    zx = sp.diff(z,x)
    print(zx)

    try:
       
        return 
    except Exception as e:
        print(e)
        print('生成失败')       
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
    parser.add_argument('--fname', type=str, default = 'test')
    parser.add_argument('--rowid', type=str, default = '2')
   
    args = parser.parse_args()
    #matha=str(args.matha)
    #matha='cos(x+8)*(9-2)'
    my_config = {'host': 'localhost', 'port': 3306, 'user': 'root',
                 'password': '123456', 'db': 'finalwork'}
    write_pic2mysql(args.fname,my_config,args.matha,args.uid,args.rowid,args.mini,args.maxi,args.qal,args.hei,args.lent)
   