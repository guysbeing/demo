(function() {
  // 获取所有具有 class="dp-ib processing_img" 的 <span> 元素
  var codeBlocks2 = document.querySelectorAll('span.processing_img');



  // 创建一个空字符串，用于存储所有文本内容
  var textContent = '';

  var aped=0;
  // 遍历每个代码块，获取其文本内容并拼接到 textContent 中
  for (var i = 0; i < codeBlocks2.length; i++) {
    var codeBlock = codeBlocks2[i];
    var codeText = codeBlock.textContent;
    if (codeText === '\n' || codeText.trim() === '') {
      continue; // 如果 codeText 为空或只包含空白字符，则跳过当前迭代
    }
    var top
    if(aped%5) top=String.fromCharCode(64+aped%5);
    else top= aped/5 +1;
    textContent +=  top  + '.' +codeText + '\n';
    aped++;
  }

  // 创建一个 Blob 对象，将 textContent 内容写入其中
  var blob = new Blob([textContent], { type: 'text/plain' });

  var downloadLink = document.createElement('a');
  downloadLink.href = URL.createObjectURL(blob);
  downloadLink.download = 'cs.txt';

  // 模拟点击下载链接
  downloadLink.click();
})();