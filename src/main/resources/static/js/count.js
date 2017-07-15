/**
 * 
 */

/**
 * 
 */

var count = new Vue({
	el : '#count',
	data : {
		
		countdetail : {
			sessionid : 'NONE',
			count : '0'
		},
		
		errorMessage:''
	},
	ready:function(){
        // code here executes once the component is rendered
        // use this in the child component
		
    },
	created : function() {
		//called after component created
		this.getCountRemote();
	},
	//Computed property is cached based on their dependency.
	computed: {
	    // a computed getter
	    getSession: function () {
	    	return this.countdetail.sessionid;
	    },
	    getCount: function () {
	    	return this.countdetail.count;
	    }
	
	}
	,
	methods : {
		getCountRemote : function() {
			this.$http.get('/getcount').then(
					function(res) {
						this.countdetail = res.json();
						this.countdetail=this.countdetail;
					},
					function (response) {
						if(response.status==500){
							this.message="Service error";
							this.errorMessage=response.json().message;
						}else{
							this.message=" cannot be retrieved";
							this.errorMessage=response.json().message;
						}
						
						console.log(response);
					}
			);

		}
	}
})