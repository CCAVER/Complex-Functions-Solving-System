import sys
import os
sys.path.append("C:\\Users\\CCAVE\\AppData\\Local\\Programs\\Python\\Python310\\Lib\\site-packages")
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
 
import matplotlib.pyplot as plt
from matplotlib.widgets import Slider, Button, RadioButtons
def sinplot(matha,mini,maxi,qal,wei,lent,path):    
    evalfunc = lambdify((x), sympify(matha), modules=['numpy'])
    fig = plt.figure()
    #ax = fig.add_subplot(1,1,1)
    fig.subplots_adjust(left=0.25, bottom=0.25)
    t = np.arange(mini, maxi, qal) 
    
    plt.grid(c='g')
    #plt.rcParams['figure.figsize'] = (wei, lent) # 单位是inches
    plt.figure(figsize=(lent,wei))
    plt.plot(t, evalfunc(t), linewidth=2, color='red')
    #plt.title(sinc)   
    #import io3 as i
    #import fucs as f
    #importlib.reload(i)
    #importlib.reload(f)
    print(path+" this is p")
    plt.title("")
    
     
 
    try:
    #↓请勿改变下行行数:在第45行
        plt.title(r'$\mathrm{f}(x) = \mathrm{ln}\left(x\right)$')
    except Exception as e:
        plt.title("")
        print(e)
        print('标题生成失败')
    finally:
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
    
    path="D:\\学习系列\\毕业论文-定档\\"+uid+"\\"
    #sys.path.append("Desktop\\论文相关\\"+uid)
    
    
    filename=name+'.png'
    evalfunc = lambdify((x), sympify(mini), modules=['numpy'])
    mini=evalfunc(mini);
    evalfunc = lambdify((x), sympify(maxi), modules=['numpy'])
    maxi=evalfunc(maxi);
    try:
        #with open(path, 'rb') as f:
            #img = f.read()
            #img=sinplot()
        img=sinplot(matha,mini,maxi,qal,wei,lent,path)
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
        #sql = "update images,imginf set images.data=%s,images.name='{0}',images.uid='{1}',imginf.matha='{3}',imginf.mini='{4}',imginf.maxi='{5}',imginf.qal='{6}',imginf.wei='{7}',imginf.lent='{8}' where images.id={2} and imginf.ImgId={2};".format(filename,uid,rowid,matha,mini,maxi,qal,wei,lent)
        sql = "update images set images.data=%s,images.name='{0}',images.uid='{1}' where images.id={2}".format(filename,uid,rowid)
        print(sql)
        cursor.execute(sql, img)
        conn.commit()
        cursor.close()
        conn.close()
        print('写入 {} 成功'.format(filename))
        
        import datetime
        from pymongo import MongoClient
        client = MongoClient()

        db = client["test"]
        
        myquery = { "_id":int(rowid) }
        newvalues = { "$set": {
                "name": filename,
                 "uid": uid,
                 "matha": matha,
                 "mini": mini,
                 "maxi":maxi,
                 "qal":qal,
                 "wei":wei,
                 "len":lent} }

        posts = db["imginf"]
        #post_id = posts.insert_one(post).inserted_id
        #post_id = posts.upda
        #print ("post id is ", post_id)
        posts.update_one(myquery, newvalues)
        print('写入MONGO成功')
        
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
    parser.add_argument('--matha', type=str, default = 'x+1')
    parser.add_argument('--uid', type=str, default = '0')
    parser.add_argument('--fname', type=str, default = 'empty')
    parser.add_argument('--rowid', type=str, default = '3')
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
    write_pic2mysql(args.fname,my_config,args.matha,args.uid,args.rowid,args.mini,args.maxi,args.qal,args.hei,args.lent)
    #python picGen.py --matha cos(x)+1 --rowid 23 --fname 1 --uid 19 --mini '-10' --maxi '10'