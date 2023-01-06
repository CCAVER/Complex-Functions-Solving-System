import argparse
import sympy
from sympy import *
import pymysql
import pymysql.cursors
from sympy import Symbol, diff, integrate, sin, cos, Function
from sympy.utilities.lambdify import lambdify, implemented_function
from sympy.abc import x
def dSolve(matha,sym,fuc):
    #x = sympy.symbols(args.sym)
    #f = sympify(args.matha)
    '''
    f = symbols(args.sym, cls=Function)#定义函数标识符
    x,g,l= symbols('x,g,l')#批量定义变量
    #eq = sympify(args.matha)
    eq = Eq(diff(f(x),x,2)+g*sin(f(x))/l,0)
    #构造单摆运动的标准常微分方程，即y"+gsin(y)/l=0,g为重力加速度，l为摆长
    #diff(函数,自变量,求导次数)
    rest=dsolve(eq, f(x))
    pprint(dsolve(eq, f(x)))#以"pretty"形式打印方程的解
    '''
    f = symbols(fuc, cls=Function)#定义函数标识符
    x = symbols(sym)#批量定义变量
    #print(sympify(args.matha))
    #eq = Eq(diff(f(x),x,2)+p*diff(f(x),x,1)+q*f(x),0)
    eq=sympify(matha)
    #构造方程，即y"+py'+qy=0
    #diff(函数,自变量,求导次数)
    #print(dsolve(eq, f(x)))
    pprint(dsolve(eq, f(x)))#以"pretty"形式打印方程的解
    
    return dsolve(eq, f(x))

def cSolve(matha,sym):
 
    x = symbols(sym)
    result = solve(matha,x)
    print(result)
    return result

def writeLim(matha,uid,rowid,type0,sym,fuc,config):
    
    try:
        conn = pymysql.connect(host=config['host'],
                               port=config['port'],
                               user=config['user'],
                               passwd=config['password'],
                               db=config['db'],
                               charset='utf8',
                               use_unicode=True)
        cursor = conn.cursor()
        name=matha
        sql = "update res set uid='{0}',name='{1}',matha='{2}',res='{3}',type='{4}',sym='{6}',fuc='{7}' where id={5}".format(uid,name,matha,res,type0,rowid,sym,fuc)
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
    parser.add_argument('--matha', type=str, default = '(diff(f(x),x,2)+2*diff(f(x),x,1)+3*f(x),0)')
    parser.add_argument('--uid', type=str, default = '0')
    parser.add_argument('--type0', type=str, default = 'd')
    parser.add_argument('--sym', type=str, default = 'x')
    parser.add_argument('--fuc', type=str, default = 'f')
    parser.add_argument('--rowid', type=str, default = '0')
    
    args = parser.parse_args()
    #matha=str(args.matha)
    #matha='cos(x+8)*(9-2)'
    my_config = {'host': 'localhost', 'port': 3306, 'user': 'root',
                 'password': '123456', 'db': 'finalwork'}
    res=""
    if args.type0=='d':
        print("Eq"+args.matha)
        res=dSolve(("Eq"+args.matha),args.sym,args.fuc)
    elif args.type0=='c':
        #cSolve('x**2 - 2*x + 1','x')
        res=cSolve(args.matha,args.sym)
    writeLim(args.matha, args.uid, args.rowid, args.type0, args.sym, args.fuc, my_config)
    #writeLim(args.matha, args.uid, args.rowid, args.type0, args.sym,my_config)
    #dSolve(args.matha,args.sym,args.fuc)
    #cSolve('x**2 - 2*x + 1','x')
    