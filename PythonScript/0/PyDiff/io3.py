#import importlib
#import latexify
import math
import os
import traceback
from io import BytesIO
import argparse
import sys
import imp
import re
#c:\users\hy\appdata\local\programs\python\python311\lib\site-packages
#sys.path.append("Desktop\\论文相关")
def changetext(a=12,b='',a0=35,uid=19):
 path='D:\\学习系列\\毕业论文-定档\\'+str(uid)+'\\pyDiff\\'
 b='  return '+b
 count=0
 #print(path)
 with open(path+'fucs.py','r',encoding='utf-8') as f:
  lines=[] # 创建了一个空列表，里面没有元素
  for line in f.readlines():
   if line!='\n':
    lines.append(line)
  f.close()
  #'Desktop\\论文相关\\fucs.py'
 with open(path+'fucs.py','w',encoding='utf-8') as f:
  for line in lines:
   if count==a:
    line = b 
    f.write('%s\n' %line)
   else:
    f.write('%s' %line) 
   count+=1;
 #import fucs as fs
 #importlib.reload(fs)
 fs=imp.load_source('fucs', path+'fucs.py')
 b0=str('             r\'$'+ re.sub(r'mathopen{}\\|mathclose{}\\', "", str(fs.Diff))+'$\'')
 #userFunc是fucs里的函数表达式       
 count1=0
 #Desktop\\论文相关\\picGen.py
 with open(path+'picGen.py','r',encoding='utf-8') as f0:
  lines=[] # 创建了一个空列表，里面没有元素
  for line in f0.readlines():
   if line!='\n':
    lines.append(line)
  f0.close()
 with open(path+'picGen.py','w',encoding='utf-8') as f0:
  for line in lines:
   if count1==a0:
    line = b0 
    f0.write('%s\n' %line)
   else:
    f0.write('%s' %line) 
   count1+=1;
#changetext(12,'cos(x**3)',35)