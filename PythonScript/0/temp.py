#该文件在\pyConn\src\main\java\com\example\pyConn\service\impl\pyServImpl.java中进行配置
#import importlib
#import latexify
import threading
import pymysql
import math
import os
import traceback
import sys
#sys.path.append("Desktop\\论文相关")
path="D:\\学习系列\\毕业论文-定档\\"#"C:\\Users\\hy\\Desktop\\论文相关\\"
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

#import picGen as p
import imp
import os
import sys
#str(sys.argv[1])
'''
def changeT(*inf):
    import io3 as i
    i.changetext(inf[0],inf[1],inf[2]) 
    print("信息载入中")
def pic(*add):
    import picGen as p
    print("图片生成中")
    importlib.reload(p)
    p.write_pic2mysql(add[0],add[1],add[2],add[3],add[4],add[5],add[6],add[7],add[8],add[9])
'''
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
    parser.add_argument('--matha', type=str, default = 'ln(x)')
    parser.add_argument('--uid', type=str, default = '0')
    parser.add_argument('--fname', type=str, default = 'empty')
    parser.add_argument('--rowid', type=str, default = '2')
    parser.add_argument('--mini', type=str, default = -10.0)
    parser.add_argument('--maxi', type=str, default = 10.0)
    parser.add_argument('--qal', type=float, default = 0.001)#线条密度
    parser.add_argument('--hei', type=float, default = 10.0)#宽
    parser.add_argument('--lent', type=float, default = 10.0)#长
    args = parser.parse_args()
    my_config = {'host': 'localhost', 'port': 3306, 'user': 'root',
                 'password': '123456', 'db': 'finalwork'}
    #import io3 as i
    #i.changetext(4,args.matha,37)#4为fucs.py第5行,37为picGen.py第38行
    qal0=args.fname
    my_tuple = (args.fname,\
                my_config,\
                args.matha,\
                args.uid,\
                args.rowid,\
                args.mini,\
                args.maxi,\
                args.qal,\
                args.hei,\
                args.lent)
    my_inf = (12,\
              args.matha,\
              44)
    '''
    try:
        #thread0 = threading.Thread(target = changeT,args =my_inf)
        thread = threading.Thread(target = pic,args =my_tuple)
       # thread0.start()
        #thread0.join(3)
        thread.start()
    except Exception as r:
        print('未知错误 %s' %(r))
    '''
    path=path+args.uid+"\\"
    #p=imp.load_source('picGen', path+'picGen.py')
    #latexify需要手动安装
    #另需安装dill:pip install dill
    a=os.system("cd D:\\学习系列\\毕业论文-定档\\"+args.uid+" && python load.py --matha "+args.matha+" --uid "+args.uid+"")#先执行文件修改
    print("cd D:\\学习系列\\毕业论文-定档\\"+args.uid+" && python load.py --matha "+args.matha+" --uid "+args.uid)
    #print("开始加载文件")
    #importlib.reload(p)
    #p=imp.load_source('picGen', path+'picGen.py')
    #print(p.__file__+" this id tmp")
    print("cd D:\\学习系列\\毕业论文-定档\\"+
                args.uid+
                " && python picGen.py --matha "+
                args.matha+
                " --rowid "+
                args.rowid+
                " --fname "+
                args.fname+
                " --uid "+
                args.uid+
                " --mini "+
                str(args.mini)+
                " --maxi "+
                str(args.maxi)+
                " --qal "+
                str(args.qal)+
                "  --hei "+
                str(args.hei)+
                " --lent "+
                str(args.lent)
                )#先执行文件修改
    a=os.system("cd D:\\学习系列\\毕业论文-定档\\"+
                args.uid+
                " && python picGen.py --matha "+
                args.matha+
                " --rowid "+
                args.rowid+
                " --fname "+
                args.fname+
                " --uid "+
                args.uid+
                " --mini "+
                str(args.mini)+
                " --maxi "+
                str(args.maxi)+
                " --qal "+
                str(args.qal)+
                "  --hei "+
                str(args.hei)+
                " --lent "+
                str(args.lent)
                )#先执行文件修改

    print("文件加载完成")
    #p.write_pic2mysql(args.fname,my_config,args.matha,args.uid,args.rowid,args.mini,args.maxi,args.qal,args.hei,args.lent)
    #sinplot(args.matha,args.mini,args.maxi,args.qal,args.hei,args.lent)
    #write_pic2mysql('DW',my_config,args.matha,'1',1)
  	#print(' 写入后再读取 ')
    #read_mysql2pic('test.png', args.rowid, my_config)
    
    #在命令行中 ↓ 将被执行
    #python temp.py --matha "(ln(x))" --rowid 23 --fname 1 --uid 19 --mini '-10' --maxi '10'