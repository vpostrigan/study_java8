set JAVA_HOME=c:\usr\local\bin\jdk1.3

set PATH=%JAVA_HOME%\bin;%path%

set EXAMPLE_HOME=c:\advjhtp1\src

rem
rem Set this to where the path to the class files begins
rem
set CURDIR=%EXAMPLE_HOME%\com\deitel\advjhtp1\idl\clock
cd %EXAMPLE_HOME%

java com.deitel.advjhtp1.idl.clock.SystemClockClient -ORBInitialPort 1050

rem
rem Take us back to where we started
rem
cd %curdir%