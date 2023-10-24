(function() {
  // 获取所有具有 class="hljs java" 的 <p> 元素
  var codeBlocks = document.querySelectorAll('div.solution-view div.pre p[class^="hljs"]');

  if (codeBlocks.length === 0) {
     return; 
  }

  // 创建一个空字符串，用于存储所有文本内容
  var textContent = '';

  // 遍历每个代码块，获取其文本内容并拼接到 textContent 中
  for (var i = 0; i < codeBlocks.length; i++) {
    var codeBlock = codeBlocks[i];
    var codeText = codeBlock.innerText;
    textContent += codeText + '\n';
  }

  // 创建一个 Blob 对象，将 textContent 内容写入其中
  var blob = new Blob([textContent], { type: 'text/plain' });

  // 提取<head>标签中的<title>元素的内容
  const title = document.title;
  // 获取包含指定类名的<div>元素
  const container = document.querySelector('div.problem-view');

  // 获取<h1>元素
  const heading = container.querySelector('h1');

  // 提取<h1>元素中的文本内容
  const headingText = heading.textContent;

  var downloadLink = document.createElement('a');
  downloadLink.href = URL.createObjectURL(blob);
  downloadLink.download = title + headingText + '.java';

  // 模拟点击下载链接
  downloadLink.click();
})();