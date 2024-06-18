/**
 * 计划列表页
 */
// Vue.http.options.emulateJSON = true;
var _planVue = new Vue({
    el: '#_plan',
    data: {
        params: {
            name: '',
            department: '',
            status: '',
            startTime: '',
            endTime: '',
            page: 1,
            limit: 10
        },
        rows: [],
        count: 0
    },
    mounted: function () {
        this.query();
    },
    methods: {
        query: function () {
            const _ = this;
            _.$http.get('http://localhost:3342/rest/plan/', {
                    params: _.params
                }).then(function (r) {
                    if (r.ok && r.body.success) {
                        _.rows = r.body.data;
                        _.count = r.body.count;
                    }
                })
        },
        add: function () {
            debugger;
            window.location.href = 'edit.html';
        },
        findSuggest: function (a, b, c) {

        },
        querySearchAsync: function (a, b, c) {
            console.log(a)
        },
        edit: function (index, row) {
            window.location.href = 'edit.html?id=' + row.id;
        },
        remove: function (index, row) {
            const _ = this;
            const url = 'http://localhost:3342/rest/plan/' + row.id + '?versionN=' + row.versionN;
            debugger;
            _.$http.delete(url).then(function (r) {
                if (r.ok && r.body.success) {
                    _.$message({
                        message: '删除成功',
                        type: 'success',
                        center: true
                    });
                    _.query();
                }
            })
        },
        detail: function (index, row) {
            window.location.href = 'detail.html?id=' + row.id;
        },
        handleSizeChange: function(limit) {
            this.params.limit = limit;
            this.query();
        },
        handleCurrentChange: function(page) {
            this.params.page = page;
            this.query();
        },
        exportE: function () {
            U.ee('plan/export', this.params);
        } 
    }
})