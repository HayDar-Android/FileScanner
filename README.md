# FileScanner [ ![Download](https://api.bintray.com/packages/haydar-android/maven/FileScanner/images/download.svg?version=1.0) ](https://bintray.com/haydar-android/maven/FileScanner/1.0/link) <a href='https://bintray.com/haydar-android/maven/FileScanner?source=watch' alt='Get automatic notifications about new "FileScanner" versions'><img src='https://www.bintray.com/docs/images/bintray_badge_color.png'></a>
## Android SD卡扫描某类文件(.mp3、.mp4...)
### 使用方法
``` gradlew

compile 'io.haydar.filescanner:FileScanner:1.0'

```
### 描述
	FileScanner是一个扫描Android /storage/emulated/0/目录中指定格式的文件。扫描结果会保存在FileScanner数据库中。
	
### 注意
``` xml
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
```
必须得申请权限

### API
``` java
FileScanner.getInstance(Context).clear();
```
清除数据库信息

``` java
 FileScanner.getInstance(this).setType(String).start(FileScanner.ScannerListener())
```
setType设置要查找文件的格式(.mp3)
start开始扫描，会自动根据数据库是否为空来进行全盘扫描还是增量扫描

```java
ArrayList<FileInfo> fileInfoArrayList= FileScanner.getInstance(MainActivity.this).getAllFiles();
```
获得扫描的文件

### 使用方法
``` java

   private void fileScanner() {
        FileScanner.getInstance(this).clear();
        FileScanner.getInstance(this).setType(".png").start(new FileScanner.ScannerListener() {
            @Override
            public void onScanBegin() {
                Log.d(TAG, "onScanBegin: ");
            }

            @Override
            public void onScanEnd() {
                Log.d(TAG, "onScanEnd: ");
                ArrayList<FileInfo> fileInfoArrayList= FileScanner.getInstance(MainActivity.this).getAllFiles();
                for (FileInfo fileInfo : fileInfoArrayList) {
                    Log.d(TAG, "fileScanner: "+fileInfo.getFilePath());
                }
            }

            @Override
            public void onScanning(String paramString, int progress) {
                Log.d(TAG, "onScanning: " + progress);
            }
        });

    }

```

