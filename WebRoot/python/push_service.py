#!usr/bin/env python
'''
@author:lucas 1273085613@qq.com
@description:鎺ㄩ�鍔熻兘
@create date:2017/09/27
杩愯鏍煎紡锛�
python push_service.py 鍙戦�鏂瑰湴鍧�鍙戦�鏂瑰瘑鐮�鏀朵欢浜哄垪琛紙鍒嗗彿鍒嗗壊锛�閭欢涓婚 閭欢鍐呭
'''
import smtplib
import urllib2
from email.header import Header
from email.mime.text import MIMEText
import time
import sys

sender_mail=sys.argv[1]
sender_mail_pwd=sys.argv[2]
receiver_mail_list=sys.argv[3]
mail_subject=sys.argv[4]
mail_content=sys.argv[5]
def send_email():
    _user = sender_mail
    _pwd  = sender_mail_pwd
    _to   = receiver_mail_list.split(';')
    msg = MIMEText(mail_content)
    msg["Subject"] = Header(mail_subject,'cp936')
    msg["From"]    = _user
    msg["To"]      = ",".join(_to)
    try:
        s = smtplib.SMTP_SSL("smtp.qq.com", 465)
        s.login(_user, _pwd)
        s.sendmail(_user, _to, msg.as_string())
        s.quit()
        print "Success!"
    except smtplib.SMTPException,e:
        print "Falied,%s"%e 

if __name__=='__main__':
    send_email()
