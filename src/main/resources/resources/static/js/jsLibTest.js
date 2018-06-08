//文件上传js库
$("#fileInput").fileinput('destroy');  // 直接销毁
$("#fileInput").fileinput({
    language: 'zh', //设置语言
    uploadUrl: '',//GLOBAL.URL+'project/saveFile.do', //上传的地址 http;//localhost:8080/project/saveFile.do
    showUpload: false, //是否显示上传按钮
    showCaption: false,//是否显示标题
    previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
    maxFileCount: 6, //表示允许同时上传的最大文件个数
    fileActionSettings:{showUpload: false}, // 控制上传按钮是否显示
    uploadExtraData: function(previewId, index) {   //额外参数
        var obj = {};
        obj.id = fodderType(); // 这里回调方法
        return obj;
    },
    preferIconicPreview: true, // 开启用图标替换预览效果
    previewFileIconSettings: { // configure your icon file extensions
        'doc': '<i class="fa fa-file-word-o text-primary"></i>',
        'xls': '<i class="fa fa-file-excel-o text-success"></i>',
        'ppt': '<i class="fa fa-file-powerpoint-o text-danger"></i>',
        'pdf': '<i class="fa fa-file-pdf-o text-danger"></i>',
        'txt': '<i class="fa fa-file-text-o text-info"></i>',
        'zip': '<i class="fa fa-file-archive-o text-muted"></i>',
        'htm': '<i class="fa fa-file-code-o text-info"></i>',
        'mov': '<i class="fa fa-file-movie-o text-warning"></i>',
        'mp3': '<i class="fa fa-file-audio-o text-warning"></i>'
    },
    previewFileExtSettings: {
        'doc': function(ext) {
            return ext.match(/(doc|docx)$/i);
        },
        'xls': function(ext) {
            return ext.match(/(xls|xlsx)$/i);
        },
        'ppt': function(ext) {
            return ext.match(/(ppt|pptx)$/i);
        },
        'zip': function(ext) {
            return ext.match(/(zip|rar|tar|gzip|gz|7z)$/i);
        },
        'htm': function(ext) {
            return ext.match(/(htm|html)$/i);
        },
        'mov': function(ext) {
            return ext.match(/(avi|mpg|mkv|mov|mp4|3gp|webm|wmv)$/i);
        },
        'mp3': function(ext) {
            return ext.match(/(mp3|wav)$/i);
        },
        'txt': function(ext) {
            return ext.match(/(txt|ini|csv|java|php|js|css)$/i);
        }
    },
    layoutTemplates:{ // 预览图片按钮控制，这里屏蔽预览按钮
        actionZoom:''
    }
});
fodderType = function() {
    return $("#debugId").val();

};

// 文件上传调用

$("#fileInput").fileinput("upload");

// 控件回显重新初始化

$("#fileInput").fileinput('destroy');
$("#fileInput").fileinput({
    language: 'zh', //设置语言
    uploadUrl: GLOBAL.URL+'project/saveFile.do?id='+bugId, //上传的地址
    showUpload: false, //是否显示上传按钮
    showCaption: false,//是否显示标题
    maxFileCount: 6, //表示允许同时上传的最大文件个数
    fileActionSettings:{showUpload: false},
    overwriteInitial: false,
    allowedPreviewTypes: ['image'],
    /*initialPreview: [
        // IMAGE RAW MARKUP
        'http://localhost:8080/amp/base/imgs/app-default.jpg',
        // IMAGE RAW MARKUP
        'http://localhost:8080/amp/base/imgs/app-default.jpg',
        // TEXT RAW MARKUP
        '123asd啊实打实',
        'http://localhost:8080/amp/base/imgs/123.docx'
    ],*/
    initialPreview : data.initialPreview,
    initialPreviewAsData: true, // allows you to set a raw markup
    initialPreviewFileType: 'image', // image is the default and can be overridden in config below
    initialPreviewDownloadUrl: GLOBAL.URL+'project/downFile.do?image={key}',
    /*initialPreviewConfig: [
        {type: "image", caption: "Image-1.jpg", size: 847000, url: "/amp/project/delFile.do", key: 1},
        {type: "image", caption: "Image-2.jpg", size: 817000, url: "/amp/project/delFile.do", key: '1519713821098wwp4h8v'},  // set as raw markup
        {type: "text", size: 1430, caption: "LoremIpsum.txt", url: "/amp/project/delFile.do", key: 3},
        {type: "office", size: 102400, caption: "123.docx", url: "/amp/project/delFile.do", key: '1519788281200pwxfx87'}
    ],*/
    initialPreviewConfig : data.initialPreviewConfig,
    preferIconicPreview: true, // this will force thumbnails to display icons for following file extensions
    previewFileIconSettings: { // configure your icon file extensions
        'doc': '<i class="fa fa-file-word-o text-primary"></i>',
        'xls': '<i class="fa fa-file-excel-o text-success"></i>',
        'ppt': '<i class="fa fa-file-powerpoint-o text-danger"></i>',
        'pdf': '<i class="fa fa-file-pdf-o text-danger"></i>',
        'txt': '<i class="fa fa-file-text-o text-info"></i>',
        'zip': '<i class="fa fa-file-archive-o text-muted"></i>',
        'htm': '<i class="fa fa-file-code-o text-info"></i>',
        'mov': '<i class="fa fa-file-movie-o text-warning"></i>',
        'mp3': '<i class="fa fa-file-audio-o text-warning"></i>'
    },
    previewFileExtSettings: { // configure the logic for determining icon file extensions
        'doc': function(ext) {
            return ext.match(/(doc|docx)$/i);
        },
        'xls': function(ext) {
            return ext.match(/(xls|xlsx)$/i);
        },
        'ppt': function(ext) {
            return ext.match(/(ppt|pptx)$/i);
        },
        'zip': function(ext) {
            return ext.match(/(zip|rar|tar|gzip|gz|7z)$/i);
        },
        'htm': function(ext) {
            return ext.match(/(htm|html)$/i);
        },
        'mov': function(ext) {
            return ext.match(/(avi|mpg|mkv|mov|mp4|3gp|webm|wmv)$/i);
        },
        'mp3': function(ext) {
            return ext.match(/(mp3|wav)$/i);
        },
        'txt': function(ext) {
            return ext.match(/(txt|ini|csv|java|php|js|css)$/i);
        }
    },
    layoutTemplates:{
        actionZoom:''
    },
    uploadExtraData: {
        img_key: "1000",
        img_keywords: "happy, nature"
    }
});
//文件上传js库