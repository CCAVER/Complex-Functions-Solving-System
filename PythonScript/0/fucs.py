import sys
import os
#
curPath = os.path.abspath(os.path.dirname(__file__))
rootPath = os.path.split(curPath)[0]
sys.path.append(rootPath)
#
#
import latexify
#务必让return在第13行 否则将会把文件改错位置
@latexify.with_latex
def f(x):
  return ln(x)
