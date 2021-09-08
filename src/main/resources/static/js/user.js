let index = {
	
	init: function(){
		$("#btn-save").on("click", ()=>{ // function(){} 안쓰고 lambda 쓰는 이유 this 바인딩 위해
			this.save();
		});
		$("#btn-login").on("click", ()=>{ // function(){} 안쓰고 lambda 쓰는 이유 this 바인딩 위해
			this.login();
		});
	},
	
	save: function(){
		//alert('test');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val(),

		};
		
		//console.log(data);
		
		
		//when ajax call default call async
		//use ajax 3 datas transfers to json request insert
		$.ajax({
			// do register request(aspect 100s)
			type:"POST",
			url:"/green/api/user",
			data:JSON.stringify(data), // http body data
			contentType:"application/json; charset=utf-8",//define body tpye(MIME)
			
			//오 밑에거 json 안했는데 왜 json으로 됨
			dataType:"json" // get response from server, response set as string(if format is json) => change to javascript
		}).done(function(res){
			alert("Register Complete");
			//console.log(res);
			location.href = "/green";
		}).fail(function(err){
			alert(JSON.stringify(err));
		}); //ajax를 이용해서 3개의 데이터를 json으로 변경하여 insert 요청 
	},

	login: function(){
		//alert('test');
		let data = {
			username: $("#username").val(),
			password: $("#password").val()
		};

		//console.log(data);


		//when ajax call default call async
		//use ajax 3 datas transfers to json request insert
		$.ajax({
			// do register request(aspect 100s)
			type:"POST",
			url:"/green/api/user/login",
			data:JSON.stringify(data), // http body data
			contentType:"application/json; charset=utf-8",//define body tpye(MIME)

			//오 밑에거 json 안했는데 왜 json으로 됨
			dataType:"json" // get response from server, response set as string(if format is json) => change to javascript
		}).done(function(res){
			alert("Login Successful");
			//console.log(res);
			location.href = "/green";
		}).fail(function(err){
			alert(JSON.stringify(err));
		}); //ajax를 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
	}
}

index.init();