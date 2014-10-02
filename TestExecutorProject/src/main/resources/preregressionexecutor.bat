
::php hipchat_notifier.php
echo startingTest %1, saving results in %2
mstest.exe /testcontainer:C:\TFS\Code\GulfstreamTestCollection\products\AirInterfaceTests\v1.0\Regression\bin\Debug\Regression.dll /test:SeatMap /resultsfile:dummyPreRegression.trx
::timeout /t 10800

