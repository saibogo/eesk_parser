#!/usr/bin/sh
path=~/IdeaProjects/eesk_data_parser
poi=/poi-5.0.0
math=/commons-math3-3.6.1
xml=/xmlbeans-4.0.0/lib
collect=/commons-collections4-4.4
compres=/commons-compress-1.20

/usr/lib/jvm/java-15-oracle/bin/java -Dvisualvm.id=611308861507684  -Dfile.encoding=UTF-8 \
-classpath $path/out/production/eesk_data_parser:\
$path$poi/poi-5.0.0.jar:\
$path$poi/poi-ooxml-5.0.0.jar:\
$path$poi/poi-excelant-5.0.0.jar:\
$path$poi/poi-ooxml-full-5.0.0.jar:\
$path$poi/poi-ooxml-lite-5.0.0.jar:\
$path$poi/poi-scratchpad-5.0.0.jar:\
$path$poi/poi-integration-5.0.0.jar:\
$path$math/commons-math3-3.6.1.jar:\
$path$math/commons-math3-3.6.1-tools.jar:\
$path$xml/xmlbeans-4.0.0.jar:\
$path$xml/xmlbeans-4.0.0-javadoc.jar:\
$path$xml/lib/xmlbeans-4.0.0-sources.jar:\
$path$collect/commons-collections4-4.4.jar:\
$path$collect/commons-collections4-4.4-tests.jar:\
$path$collect/commons-collections4-4.4-javadoc.jar:\
$path$collect/commons-collections4-4.4-sources.jar:\
$path$collect/commons-collections4-4.4-test-sources.jar:\
$path$compres/commons-compress-1.20.jar \
Main