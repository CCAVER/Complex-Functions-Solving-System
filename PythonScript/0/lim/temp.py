import argparse
import sympy
from sympy import *
import pymysql
import pymysql.cursors
from sympy import Symbol, diff, integrate, sin, cos, Function
from sympy.utilities.lambdify import lambdify, implemented_function
from sympy.abc import x

def writeLim(matha,uid,rowid,lim,sym,config):
    """
    读取图片写入数据库
    :param name: 读取的图片的名字
    :param config: 数据库连接配置信息
    :param matha:用户提供的函数
    :param uid:使用用户的id
    :param rowid:图片的id
    :return: None
    """
    x = sympy.symbols(args.sym)
    f = sympify(args.matha)
    #print(sympy.limit(f,x,sympy.oo))
    res=sympy.limit(f,x,sympify(args.lim))
    print(res)
    try:
        conn = pymysql.connect(host=config['host'],
                               port=config['port'],
                               user=config['user'],
                               passwd=config['password'],
                               db=config['db'],
                               charset='utf8',
                               use_unicode=True)
        cursor = conn.cursor()
        name="lim("+matha+"):"+sym+"→"+lim
        sql = "update lim set uid='{0}',name='{1}',matha='{2}',result='{3}',lim='{4}',sym='{6}' where id={5}".format(uid,name,matha,res,lim,rowid,sym)
        print(sql)
        cursor.execute(sql)
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
    parser.add_argument('--matha', type=str, default = '1/x')
    parser.add_argument('--uid', type=str, default = '0')
    parser.add_argument('--rowid', type=str, default = '1')
    parser.add_argument('--lim', type=str, default = 'oo')
    parser.add_argument('--sym', type=str, default = 'x')
    
    args = parser.parse_args()
    #matha=str(args.matha)
    #matha='cos(x+8)*(9-2)'
    my_config = {'host': 'localhost', 'port': 3306, 'user': 'root',
                 'password': '123456', 'db': 'finalwork'}
    
    writeLim(args.matha, args.uid, args.rowid, args.lim, args.sym,my_config)
    