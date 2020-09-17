<template>
    <div>
        <el-form ref="loginForm" :model="form" :rules="rules" label-width="80px" class="login-box">
            <h3 class="login-title">欢迎登录</h3>
            <el-form-item label="用户名" prop="username">
                <el-input type="text" placeholder="请输入用户名" v-model="form.username"/>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input type="password" placeholder="请输入密码" v-model="form.password"/>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit('loginForm')">登录</el-button>
                没有账号？
                <el-link type="warning" @click="register()">点击注册</el-link>
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
            register() {
                this.$router.push("/Register");
            },
            onSubmit(formName) {
                let _this = this;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.post("http://120.78.161.225:8181/admin/login", _this.form)
                            .then(function (resp) {
                                if (resp.data === "success") {
                                    _this.$alert(_this.form.username + "，欢迎您！", "提示信息", {
                                        confirmButtonText: '确定',
                                        callback: action => {
                                            _this.$router.push({
                                                name: '用户管理',
                                                params: {
                                                    username: _this.form.username,
                                                    password: _this.form.password
                                                }
                                            });
                                        }
                                    });
                                } else {
                                    _this.$alert("用户名或密码输入错误！", "警告", {
                                        confirmButtonText: '确定',
                                        callback: action => {
                                            _this.$router.push('/');
                                        }
                                    });
                                }
                            });
                    } else {
                        return false;
                    }
                });
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