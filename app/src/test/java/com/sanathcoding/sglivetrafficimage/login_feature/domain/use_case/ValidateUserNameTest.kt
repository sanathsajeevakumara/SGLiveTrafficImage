package com.sanathcoding.sglivetrafficimage.login_feature.domain.use_case

import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class ValidateUserNameTest {

    private lateinit var validateUserName: ValidateUserName

    @Before
    fun setUp() {
        validateUserName = ValidateUserName()
    }

    @Test
    fun `if username is blank, return false`() {
        val result = validateUserName.execute("")
        assertEquals(result.isSuccessful, false)
    }

    @Test
    fun `if username less than 3 characters, return false`() {
        val result = validateUserName.execute("12")
        assertEquals(result.isSuccessful, false)
    }

    @Test
    fun `if username more than 20 characters, return false`() {
        val result = validateUserName.execute("qazwsxedcrfvtgbyhnujm")
        assertEquals(result.isSuccessful, false)
    }

    @Test
    fun `if username valid, return true`() {
        val result = validateUserName.execute("XBox")
        assertEquals(result.isSuccessful, true)
    }
}