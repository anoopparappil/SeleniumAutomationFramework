FOR /F "tokens=4 delims= " %%i in ('route print ^| find " 0.0.0.0"') do set localIp=%%i
echo IP Address is: %localIp%
java -jar selenium-server-standalone-3.14.0.jar -role node -hub http://%localIp%:4444/grid/register