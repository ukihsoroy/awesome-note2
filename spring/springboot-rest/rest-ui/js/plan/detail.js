/**
 * 计划新建/编辑页面
 */

var _planVue = new Vue({
    el: '#_plan',
    data: {
        id: '1',
        form: {
            planCode: '',
            planName: '',
            submitDt: '',
            submitUser: '',
            planStatus: '',
            deleteI: '',
            versionN: '',
            createUserId: '',
            createDt: '',
            modifyUserId: '',
            modifyDt: ''
        },
        rules: {
            planCode: [{required: true, message: '请输入计划编号', trigger: 'blur'}],
            planName: [{required: true, message: '请输入计划名称', trigger: 'blur'}],
            submitDt: [{required: true, message: '请选择计划日期', trigger: 'change'}],
            submitUser: [{required: true, message: '请输入提交人', trigger: 'blur'}],
            planStatus: [{required: true, message: '请选择计划状态', trigger: 'change'}],
            deleteI: [{required: true, message: '请选择删除状态', trigger: 'change'}],
            versionN: [{required: true, message: '请输入版本号', trigger: 'blur'}],
            createUserId: [{required: true, message: '请输入创建用户', trigger: 'blur'}],
            createDt: [{required: true, message: '请选择创建日期', trigger: 'change'}],
            modifyUserId: [{required: true, message: '请输入修改用户', trigger: 'blur'}],
            modifyDt: [{required: true, message: '请选择修改日期', trigger: 'change'}],
        }
    },
    mounted: function () {
        this.editPage();
    },
    methods: {
        editPage: function () {
            if (this.id) {
                this.form = {
                    planCode: '111',
                    planName: '111',
                    submitDt: '2018-03-05 12:00:00',
                    submitUser: '111',
                    planStatus: '1',
                    deleteI: 'N',
                    versionN: '1',
                    createUserId: '111',
                    createDt: '2017-03-11 12:00:00',
                    modifyUserId: '123',
                    modifyDt: '2017-03-11 12:00:00'
                };
            }
        },
        back: function () {
            window.location.href = 'list.html';
        }
    }
})




/*---------------------------------------表单demo------------------------------------------------
var _formVue = new Vue({
    el: '#_form',
    data: {
        form: {
            name: '',
            region: '',
            date1: '',
            date2: '',
            delivery: false,
            type: [],
            resource: '',
            desc: ''
        },
        rules: {
            name: [
                { required: true, message: '请输入活动名称', trigger: 'blur' },
                { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
            ],
            region: [
                { required: true, message: '请选择活动区域', trigger: 'change' }
            ],
            date: [
                { type: 'date', required: true, message: '请选择日期', trigger: 'change' }
            ],
            time: [
                { type: 'date', required: true, message: '请选择时间', trigger: 'change' }
            ],
            type: [
                { type: 'array', required: true, message: '请至少选择一个活动性质', trigger: 'change' }
            ],
            resource: [
                { required: true, message: '请选择活动资源', trigger: 'change' }
            ],
            desc: [
                { required: true, message: '请填写活动形式', trigger: 'blur' }
            ]
        }
    },
    methods: {
        submitForm: function(formName) {
            this.$refs[formName].validate(function(valid){
                if (valid) {
                    alert('submit!');
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        resetForm: function(formName) {
            this.$refs[formName].resetFields();
        }
    }
})
*/
