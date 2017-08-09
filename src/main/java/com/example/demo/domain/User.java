package com.example.demo.domain;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by finup on 2017/7/25.
 */
public class User implements java.io.Serializable{


        private static final long serialVersionUID = 1L;

        private Long id;

        @NotNull(message = "编码值不能为空")
        private String code;

        @NotNull(message = "名称不能为空")
        private String name;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
}
