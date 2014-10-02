
::php hipchat_notifier.php
echo startingTest %1, saving results in %2
mstest.exe /testcontainer:D:\Code\GulfstreamTestCollection\products\AirInterfaceTests\v1.0\Features\FPP\bin\Debug\FPP.dll /test:%1 /resultsfile:%2
::timeout /t 10800

