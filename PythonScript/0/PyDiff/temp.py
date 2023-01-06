import os
import sys
import sympy as sp
from sympy import *

from sympy import Symbol, diff, integrate, sin, cos, Function
from sympy.utilities.lambdify import lambdify, implemented_function
from sympy.abc import x
path="D:\\学习系列\\毕业论文-定档\\"
import argparse
if __name__ == '__main__':
    #--------↓防止在命令行中找不到包--------
    curPath = os.path.abspath(os.path.dirname(__file__))
    rootPath = os.path.split(curPath)[0]
    sys.path.append(rootPath)
    
    
    fname='DW'
    rowid=1
    uid=1

    parser = argparse.ArgumentParser(description='manual to this script')
    parser.add_argument('--matha', type=str, default = 'ln(x)-cos(x)')
    parser.add_argument('--uid', type=str, default = '0')
    parser.add_argument('--rowid', type=str, default = '1')
    #parser.add_argument('--fname', type=str, default = 'FromTemp')
    
    args = parser.parse_args()
    #求导函数
    x= sp.symbols('x')
    z = sympify(args.matha)
    zx = sp.diff(z,x)
    print(zx)
    
    
    my_config = {'host': 'localhost', 'port': 3306, 'user': 'root',
                 'password': '123456', 'db': 'finalwork'}
   
    path=path+args.uid+"\\"

    a=os.system("cd D:\\学习系列\\毕业论文-定档\\"+args.uid+
                "\\pyDiff && python load.py --matha \"("+
                str(zx)+
                ")\" --uid "+
                args.uid+"")#先执行文件修改
    print("文件加载完成")
    print("cd D:\\学习系列\\毕业论文-定档\\"+
                args.uid+
                "\\pyDiff && python picGen.py"+
                " --diff ("+
                str(zx)+
                ") --uid "+
                args.uid+
                " --fx "+
                args.matha+
                " --rowid "+
                args.rowid
                )#先执行文件修改
    a=os.system("cd D:\\学习系列\\毕业论文-定档\\"+
                args.uid+
                "\\pyDiff && python picGen.py"+
                " --diff \"("+
                str(zx)+
                ")\" --uid "+
                args.uid+
                " --fx "+
                args.matha+
                " --rowid "+
                args.rowid
                )#先执行文件修改
    print("文件执行完成")
    
    
   #cd C:\Users\hy\Desktop\论文相关\0\PyDiff && python temp.py --matha sin(5*x)+7 --uid 0 --rowid 1