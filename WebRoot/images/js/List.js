function List(container, data) {
		this.autoRefresh = true;
		this.container = container;
		this.data = [];
		this.updateAll(data);
		var _null_func_ = function() {};
		this.normalFunc = _null_func_;
		this.editFunc = _null_func_;
	}
	List._count = 10000;
	List.prototype.updateAll = function(data) {
		if(data && data.length > 0) {
			var b = this.autoRefresh;
			this.autoRefresh = false;
			for(var i = 0; i < data.length; i++) {
				this.add(data[i]);
			}
			this.autoRefresh = b;
		}
	}
	List.prototype.setNormalTemplate = function(str) {
		this.normalFunc = this.buildFunction(str);
	}
	List.prototype.setEditTemplate = function(str) {
		this.editFunc = this.buildFunction(str);
	}
	List.prototype.buildFunction = function(str) {
		var body = 	 "return `" + str.replace(/\{\{\s*/g, "`+(").replace(/\s*\}\}/g, ")+`") + "`";
		return new Function("item", "temp", body);
	}
	List.prototype.changeStatus = function(id, status) {
		var item = this.find(id);
		if(!item) return;
		if(status == "edit") {
			for(var i = 0; i < this.data.length; i++) {
				this.data[i]._status = "normal";
			}
		
		}
		item._status = status;
		if(this.autoRefresh) this.refresh();
	}
	List.prototype.refresh = function() {
		this.container.innerHTML = "";
		var len = this.data.length;
		if(len == 0) return;
		var str = "";
		for(var i = 0; i < len; i++) {
			var item = this.data[i];
			var temp = {};
			temp.__index = i;
			if(item._status && item._status == "edit") {
				// 编辑
				str += this.editFunc(item, temp);
			}else {
				// 正常
				str += this.normalFunc(item, temp);
			}
		}
		this.container.innerHTML = str;
	}
	List.prototype.add = function(item) {
		item._status = "normal";
		item._id = List._count++;
		this.data.push(item);
		if(this.autoRefresh) this.refresh();
	}
	List.prototype.delete = function(id) {
		for(var i = 0; i < this.data.length; i++) {
			if(this.data[i]._id == id) {
				this.data.splice(i, 1);
			}
		}
		if(this.autoRefresh) this.refresh();
	}
	List.prototype.update = function(id, options) {
		var item = this.find(id);
		if(!item) return;
		for(var key in options) {
			item[key] = options[key];
		}
		item._status = "normal";
		if(this.autoRefresh) this.refresh();
	}
	List.prototype.find = function(id) {
		for(var i = 0; i < this.data.length; i++) {
			if(this.data[i]._id == id) return this.data[i];
		}
		return null;
	}