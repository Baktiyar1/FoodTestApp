package com.example.foodtestapp.ui.manager.toast

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class ToastManagerTest {

    @MockK
    private lateinit var showToastUseCase: ShowToastUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `show toast should show a toast with the given message`() {
        every { showToastUseCase.showToast(TEST_MESSAGE) } returns Unit
        showToastUseCase.showToast(TEST_MESSAGE)
        verify(exactly = 1) { showToastUseCase.showToast(TEST_MESSAGE) }
    }


    companion object {
        private const val TEST_MESSAGE = "Hello world!"
    }
}