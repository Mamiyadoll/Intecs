/************ separateValue ************/
/* elementsName(必須): 区切る対象のname属性値 */
function separateValue(elementsName){
	const elements = document.getElementsByName(elementsName);
	for(i=0; i<elements.length; i++){
		elements[i].innerHTML = Number(elements[i].innerHTML).toLocaleString()
	}
}


/************ separatePost ************/
function separatePost(elementsName){
	const elements = document.getElementsByName(elementsName);
	for(i=0; i<elements.length; i++){
		const postCodeRaw = elements[i].innerHTML;
		const postCode = postCodeRaw.substr(0,3)+"-"+postCodeRaw.substr(3);
		elements[i].innerHTML = postCode;
	}
}


/************ enableSubmit ************/
/* formId(必須): チェックするformタグのid名 */
/* emptyBackgroundColor(任意): 空欄のinputタグの背景色を設定したい場合のみセットしてください */
/* buttonNumber(任意): submitボタンなどのvalueが空欄でなければ、基本的には値を渡さなくても問題ないです */
/*                     チェックするformタグ内のinputタグのうち、チェック対象外にするinputタグの数(submitボタンやresetボタンなど) */
/*                     ※buttonNumberに引数を渡さなかった場合は、関数内部でデフォルト値の1が設定されます */
function enableSubmit(formId, emptyBackgroundColor, buttonNumber){

	// 定義
	const element = document.getElementById(formId);
	let tagname = new Array();
	let inputId = new Array();
	let submitId = new Array();
	let button;

	// チェック対象外のinputタグ設定
	if(buttonNumber>=0){
		button = buttonNumber;
	}else{
		button = 1;		//デフォルト
	}

	// inputタグを探す
	for(i=0; i<element.children.length; i++){		//対象Formの子要素のタグをチェック
		tagname.push(element.children[i].nodeName);
	}
	for(i=0; i<tagname.length; i++){				//INPUTタグだったら要素を取得、そうじゃなければ下層のノードを取得
		if(tagname[i]=='INPUT'){
			inputId.push(element.children[i]);
		}else{
			if(element.children[i].hasChildNodes){
				for(j=0; j<element.children[i].children.length; j++){		//孫要素のINPUT探索
					if(element.children[i].children[j].nodeName == 'INPUT'){
						inputId.push(element.children[i].children[j]);
					}
				}
			}
		}
	}

	// submitボタンを取得
	for(i=0; i<inputId.length; i++){
		if(inputId[i].type == 'submit'){
			submitId.push(inputId[i]);
		}
	}

	// チェック対象のinputが全て埋められているか
	const formFilled = inputId.length-button;
	let countFilled = 0;
	for(i=0; i<formFilled; i++){
		if(inputId[i].value != ''){
			countFilled++;
			if(emptyBackgroundColor != null){
				inputId[i].style.backgroundColor = 'rgba(0,0,0,0)';
			}
		}else{
			if(emptyBackgroundColor != null){
				inputId[i].style.backgroundColor = emptyBackgroundColor;
			}
		}
	}

	// submitボタンの有効、無効
	for(i=0; i<submitId.length; i++){
		if(formFilled>countFilled){
			submitId[i].disabled = true;
		}else{
			submitId[i].disabled = false;
		}
	}
}


/************ ページ読み込み完了時に実行 ************/
/* html/jspのbodyタグにはonload属性を付与しないでください。 */
window.onload = function(){
	separateValue('separateValue');
	separatePost('separatePost');
}

