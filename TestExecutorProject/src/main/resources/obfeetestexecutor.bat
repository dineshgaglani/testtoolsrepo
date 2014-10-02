
::php hipchat_notifier.php
echo startingTest %1, saving results in %2
mstest.exe /testcontainer:D:\Code\GulfstreamTestCollection\products\AirInterfaceTests\v1.0\OBFee\bin\OBFee.dll /test:%1 /resultsfile:%2
::timeout /t 10800

