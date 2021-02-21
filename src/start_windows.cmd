@set main=G:\IdeaProject\eesk_parser
@set poi=\poi-5.0.0
@set math=\commons-math3-3.6.1

"D:\Program Files\Java\jdk-14\bin\java.exe" ^
-Dfile.encoding=UTF-8 ^
-classpath ^
%main%\out\production\eesk_parser;^
%main%%poi%\poi-5.0.0.jar;^
%main%%poi%\poi-ooxml-5.0.0.jar;^
%main%%poi%\poi-excelant-5.0.0.jar;^
%main%%poi%\poi-ooxml-full-5.0.0.jar;^
%main%%poi%\poi-ooxml-lite-5.0.0.jar;^
%main%%poi%\poi-scratchpad-5.0.0.jar;^
%main%%poi%\poi-integration-5.0.0.jar;^
%main%\commons-collections4-4.4\commons-collections4-4.4.jar;^
%main%\commons-compress-1.20\commons-compress-1.20.jar;^
%main%%math%\commons-math3-3.6.1.jar;^
%main%%math%\commons-math3-3.6.1-tools.jar;^
%main%\xmlbeans-4.0.0\lib\xmlbeans-4.0.0.jar ^
Main