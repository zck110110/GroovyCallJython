#! /usr/bin/env jython
# jythonDemo.py
# this file show the how groovy call a jython method
# chikai 2016.09.21

import pythonpathcase
def feedback(user, password):
    print 'this is print in jython: user = ' + user
    print 'this is print in jython: password = ' + password
    return True