@echo off
set path=C:\Users\t-nhassan\.m2\repository\allure\allure-2.18.1\bin;C:\Users\t-nhassan\.p2\pool\plugins\org.eclipse.justj.openjdk.hotspot.jre.full.win32.x86_64_17.0.2.v20220201-1208\jre\bin;%path%
allure serve allure-results
pause
exit