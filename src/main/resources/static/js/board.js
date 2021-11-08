let index = {
	
	init: function(){
		$("#btn-save").on("click", ()=>{ // function(){} 안쓰고 lambda 쓰는 이유 this 바인딩 위해
			this.save();
		});
		$("#btn-delete").on("click", ()=>{
			this.deleteById();
		});
		$("#btn-update").on("click", ()=>{
			this.update();
		});
	},
	
	save: function(){
		//alert('test');
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		};

		//when ajax call default call async
		//use ajax 3 datas transfers to json request insert
		$.ajax({
			// do register request(aspect 100s)
			type:"POST",
			url:"/api/board",
			data:JSON.stringify(data), // http body data
			contentType:"application/json; charset=utf-8",//define body tpye(MIME)
			dataType:"json" // get response from server, response set as string(if format is json) => change to javascript
		}).done(function(res){
			alert("Post Complete");
			location.href = "/";
		}).fail(function(err){
			alert(JSON.stringify(err));
		}); //ajax를 이용해서 3개의 데이터를 json으로 변경하여 insert 요청 
	},

	deleteById: function(){
		let id = $("#id").text();

		$.ajax({
			// do register request(aspect 100s)
			type:"DELETE",
			url:"/api/board/"+id,
			dataType:"json" // get response from server, response set as string(if format is json) => change to javascript
		}).done(function(res){
			alert("Delete Complete");
			location.href = "/";
		}).fail(function(err){
			alert(JSON.stringify(err));
		}); //ajax를 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
	},

	update: function(){
		//alert('test');
		let id = $("#id").val();
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		};

		//when ajax call default call async
		//use ajax 3 datas transfers to json request insert
		$.ajax({
			// do register request(aspect 100s)
			type:"PUT",
			url:"/api/board/"+id,
			data:JSON.stringify(data), // http body data
			contentType:"application/json; charset=utf-8",//define body tpye(MIME)
			dataType:"json" // get response from server, response set as string(if format is json) => change to javascript
		}).done(function(res){
			alert("Post Edit Complete");
			location.href = "/board/"+id;
		}).fail(function(err){
			alert(JSON.stringify(err));
		}); //ajax를 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
	},
}


index.init();