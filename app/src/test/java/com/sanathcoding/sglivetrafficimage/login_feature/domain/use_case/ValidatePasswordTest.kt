package com.sanathcoding.sglivetrafficimage.login_feature.domain.use_case

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ValidatePasswordTest {

    private lateinit var validatePassword: ValidatePassword

    @Before
    fun setup() {
        validatePassword = ValidatePassword()
    }

    @Test
    fun `if password is blank, return false`() {
        val result = validatePassword.execute("")
        assertEquals(result.isSuccessful, false)
    }

    @Test
    fun `if password consist only digits, return false`() {
        val result = validatePassword.execute("123")
        assertEquals(result.isSuccessful, false)
    }

    @Test
    fun `if password consist only letters, return false`() {
        val result = validatePassword.execute("abc")
        assertEquals(result.isSuccessful, false)
    }

    @Test
    fun `if password consist only special characters, return false`() {
        val result = validatePassword.execute("!@#$")
        assertEquals(result.isSuccessful, false)
    }

    @Test
    fun `if password consist only upper case letters, return false`() {
        val result = validatePassword.execute("ASDF")
        assertEquals(result.isSuccessful, false)
    }

    @Test
    fun `if password consist only lower case letters, return false`() {
        val result = validatePassword.execute("asdf")
        assertEquals(result.isSuccessful, false)
    }

    @Test
    fun `if password consist letter digits special chars, return true`() {
        val result = validatePassword.execute("1qaz!QAZ")
        assertEquals(result.isSuccessful, true)
    }

}