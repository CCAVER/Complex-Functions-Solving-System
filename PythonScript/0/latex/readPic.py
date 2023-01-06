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
        cursor.execute("select img from latex where uid = '{}'".format(filename))
        #cursor.execute("select data from images where id = '{}'".format(filename))
        res = cursor.fetchone()[0]
        with open(path, 'wb') as f:
            f.write(res)
        print('从数据库中读取 {} 成功'.format(filename))
        print(f)
    except Exception as e:
        print(e)
        print('读取数据库中的图片失败')


if __name__ == '__main__':

    fname='DW'
    rowid=1
    uid=1

    parser = argparse.ArgumentParser(description='manual to this script')
    parser.add_argument('--rowid', type=str, default = '0')
    args = parser.parse_args()
    #matha=str(args.matha)
    #matha='cos(x+8)*(9-2)'
    my_config = {'host': 'localhost', 'port': 3306, 'user': 'root',
                 'password': '123456', 'db': 'finalwork'}
    read_mysql2pic('D:\\学习系列\\毕业论文-定档\\0\\latex\\test.png', args.rowid, my_config)
    #cd C:\Users\hy\Desktop\论文相关\0\latex && python temp.py --matha sin(5*x)+7 --fname FromTemp --uid 0