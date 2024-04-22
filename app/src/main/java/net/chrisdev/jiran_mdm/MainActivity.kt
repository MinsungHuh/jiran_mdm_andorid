package net.chrisdev.jiran_mdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.jiransecurity.library.IMDMSupportService
import com.jiransoft.mdm.library.ErrorCode
import com.jiransoft.mdm.library.MDMLib

/**
 * @author Minsung Huh
 * @since 2024. 04. 22
 *
 * 지란지교 MDM 연동 가이드 입니다.
 *
 */
class MainActivity : AppCompatActivity() {
    val dev_host = "dev-mam.kbfgone.com" // 개발
    val stg_host = "stg-mam.kbfgone.com" // 스테이징
    val prod_host = "mam.kbfgone.com" // 운영
    val port = 44300 // 포트정보
    val comp_code = "23062300" // 회사코드

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // #1 MDMLib 라이브러리 init
        val instance = MDMLib.getInstance(this, dev_host, comp_code)

        // #2 call mangobanana api
        instance.setOnMangobananaCompleteListener { code, message ->
            if (code == ErrorCode.COMMON_SUCCESS_CODE) {
                // 인증 성공
            } else {
                // 인증 실패
                // TODO message parameter Alert 표시
            }
        }

        // #3
        instance.mangobanana(this, "5522458")

        lifecycle.addObserver(MyObserver())
    }
}