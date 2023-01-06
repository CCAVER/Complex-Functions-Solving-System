import sys
import argparse
import threading
#import importlib
import imp
if __name__ == '__main__':
    #rowid=int(sys.argv[1])#id
    #fname=str(sys.argv[2])#保存的文件名
    #uid=str(sys.argv[3])#用户id
    fname='DW'
    rowid=1
    uid=1

    parser = argparse.ArgumentParser(description='manual to this script')
    parser.add_argument('--matha', type=str, default = 'ln(x)')
    parser.add_argument('--uid', type=int, default = 0)
    args = parser.parse_args()
    #matha=str(args.matha)
    #matha='cos(x+8)*(9-2)'
    my_config = {'host': 'localhost', 'port': 3306, 'user': 'root',
                 'password': '123456', 'db': 'finalwork'}
    
    inf = (12,\
              args.matha,\
              44,\
              args.uid)
    print('LOADING')
    path="D:\\学习系列\\毕业论文-定档\\"+str(args.uid)+"\\io3.py"
    #sys.path.append("Desktop\\论文相关\\"+str(args.uid)+"\\")
    #i=imp.load_source('io3', path)
    #import io3 as i
    #importlib.reload(i)
    i=imp.load_source('io3', path)
    '''
    lines=['Desktop\\论文相关\\19\\', 'Desktop\\论文相关']
    for a in lines:
        sys.path.remove(a)
    print(sys.path)'''
    print('the matha is'+args.matha)
    i.changetext(inf[0],inf[1],inf[2],inf[3]) 
