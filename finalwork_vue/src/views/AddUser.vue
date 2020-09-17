<template>
    <div style="width: 40%;">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px"
                 class="demo-ruleForm">
            <el-form-item label="姓名" prop="name">
                <el-input v-model="ruleForm.name"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="gender">
                <el-radio-group v-model="ruleForm.gender">
                    <el-radio label="男"></el-radio>
                    <el-radio label="女"></el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="年龄" prop="age">
                <el-select v-model="ruleForm.age" placeholder="请选择用户年龄">
                    <el-option v-for="item in 127" :value="item"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="籍贯" prop="address">
                <el-select v-model="ruleForm.address" placeholder="请选择用户籍贯">
                    <el-option v-for="item in array" :value="item"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="QQ" prop="qq">
                <el-input v-model.number="ruleForm.qq"></el-input>
            </el-form-item>
            <el-form-item label="email" prop="email">
                <el-input v-model="ruleForm.email"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">立即添加</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                array: ["北京市", "天津市", "河北省"
                    , "山西省", "辽宁省"
                    , "吉林省", "上海市"
                    , "江苏省", "浙江省", "安徽省"
                    , "福建省", "江西省", "山东省"
                    , "河南省", "湖北省", "湖南省"
                    , "广东省", "海南省"
                    , "重庆市", "四川省", "贵州省"
                    , "云南省", "西藏自治区", "陕西省"
                    , "甘肃省", "青海省", "台湾省"
                    , "黑龙江省", "内蒙古自治区"
                    , "广西壮族自治区", "宁夏回族自治区"
                    , "新疆维吾尔自治区", "香港特别行政区"
                    , "澳门特别行政区"],
                ruleForm: {
                    name: '',
                    gender: '',
                    age: '',
                    address: '',
                    qq: '',
                    email: ''
                },
                rules: {
                    name: [
                        {required: true, message: '请输入用户姓名', trigger: 'blur'},
                    ],
                    gender: [
                        {required: true, message: '请选择用户性别', trigger: 'blur'}
                    ],
                    age: [
                        {required: true, message: '请选择用户年龄', trigger: 'blur'}
                    ],
                    address: [
                        {required: true, message: '请选择用户籍贯', trigger: 'blur'}
                    ],
                    qq: [
                        {required: true, message: '请输入用户QQ', trigger: 'blur'},
                        {type: 'number', message: '请输入正确的QQ', trigger: ['change', 'blur']}
                    ],
                    email: [
                        {required: true, message: '请输入用户邮箱地址', trigger: 'blur'},
                        {type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change']}
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let _this = this;
                        axios.post("http://120.78.161.225:8181/user/addUser", _this.ruleForm).then(function (resp) {
                            if (resp.data === "success") {
                                _this.$alert("添加用户成功！", "提示信息", {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.push('/FindAll');
                                    }
                                });
                            } else {
                                _this.$alert("添加失败！请联系技术人员维护！", "警告", {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.push('/FindAll');
                                    }
                                });
                            }
                        })
                    } else {
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }
    }
</script>