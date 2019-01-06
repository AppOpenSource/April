#!/usr/bin/env bash
echo 'adb uninstall com.abt.clock_memo'
adb uninstall com.abt.clock_memo
echo 'apk uninstalled'

echo 'adb install'
adb install /Users/huangweiqi/StudioProjects/April/release/ClockMemo_V2.0.2_20180603_B.apk
echo 'apk installed'
