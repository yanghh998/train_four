<template>
    <div>
        <el-form ref="registerForm" :model="form" :rules="rules" label-width="80px"
                 class="login-box">
            <el-page-header @back="goBack"></el-page-header>
            <h3 class="login-title">欢迎注册</h3>
            <el-form-item label="用户名" prop="username">
                <el-input type="text" placeholder="请输入用户名" v-model="form.username"/>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input type="password" placeholder="请输入密码" v-model="form.password"/>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit('registerForm')">注册</el-button>
            </el-form-item>
        </el-form>

        <el-dialog
                title="温馨提示"
                :visible.sync="dialogVisible"
                width="30%"
                :before-close="handleClose">
            <span>请输入账号和密码</span>
            <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                form: {
                    username: '',
                    password: ''
                },

                rules: {
                    username: [
                        {required: true, message: '账号不可为空', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '密码不可为空', trigger: 'blur'}
                    ]
                },
                dialogVisible: false,
            }
        },
        methods: {
            onSubmit(formName) {
                let _this = this;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.post("http://120.78.161.225:8181/admin/register", _this.form)
                            .then(function (resp) {
                                if (resp.data === false) {
                                    _this.$alert('当前用户名已注册！', '警告', {
                                        confirmButtonText: '确定',
                                        callback: action => {
                                            _this.$router.push('/Register');
                                        }
                                    });
                                }else {
                                    _this.$alert('注册成功！', '消息', {
                                        confirmButtonText: '确定',
                                        callback: action => {
                                            _this.$router.push('/');
                                        }
                                    });
                                }
                                console.log(resp);
                            })
                    } else {
                        return false;
                    }
                });
            },
            goBack() {
                this.$router.push("/");
            }
        }
    }
</script>

<style>
    .login-box {
        border: 1px solid #DCDFE6;
        width: 350px;
        margin: 180px auto;
        padding: 35px 35px 15px 35px;
        border-radius: 5px;
        -webkit-border-radius: 5px;
        -moz-border-radius: 5px;
        box-shadow: 0 0 25px #909399;
    }

    .login-title {
        text-align: center;
        margin: 0 auto 40px auto;
        color: #303133;
    }
</style>