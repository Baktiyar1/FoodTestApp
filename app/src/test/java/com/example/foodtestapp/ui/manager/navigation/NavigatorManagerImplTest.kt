package com.example.foodtestapp.ui.manager.navigation

import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class NavigatorManagerImplTest {

    @MockK
    private lateinit var navigatorManager: NavigatorManager

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `should not null destination flow`() = runBlocking {
        val testDestinationFlow =
            MutableStateFlow(TEST_DESTINATION to TEST_IS_CLEAR_BACK_STACK).asStateFlow()
        every { navigatorManager.destinationFlow() } returns testDestinationFlow

        val actual = navigatorManager.destinationFlow().firstOrNull()
        val expected = MutableStateFlow(TEST_DESTINATION to TEST_IS_CLEAR_BACK_STACK).asStateFlow()
            .firstOrNull()

        assert(expected == actual)
    }

    @Test
    fun `navigateTo should emit a new value to the destinationFlow`() = runBlocking {
        val testDestinationFlow = MutableStateFlow(TEST_DESTINATION to TEST_IS_CLEAR_BACK_STACK)
        every { navigatorManager.destinationFlow() } returns testDestinationFlow
        every { navigatorManager.navigateTo(TEST_DESTINATION, TEST_IS_CLEAR_BACK_STACK) } just Runs

        navigatorManager.navigateTo(TEST_DESTINATION, TEST_IS_CLEAR_BACK_STACK)

        verify(exactly = 1) {
            navigatorManager.navigateTo(TEST_DESTINATION, TEST_IS_CLEAR_BACK_STACK)
        }

        val destinationFlowActual = navigatorManager.destinationFlow().firstOrNull()

        assert(testDestinationFlow.firstOrNull() == destinationFlowActual)
    }

    companion object {
        private const val TEST_DESTINATION = "destination"
        private const val TEST_IS_CLEAR_BACK_STACK = true
    }
}