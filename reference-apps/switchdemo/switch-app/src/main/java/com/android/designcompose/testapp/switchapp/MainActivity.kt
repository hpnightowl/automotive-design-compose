/*
 * Copyright 2023 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.designcompose.testapp.switchapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.android.designcompose.DesignSettings
import com.android.designcompose.TapCallback
import com.android.designcompose.annotation.Design
import com.android.designcompose.annotation.DesignComponent
import com.android.designcompose.annotation.DesignDoc
import com.android.designcompose.annotation.DesignVariant
import com.android.designcompose.testapp.switchapp.ui.theme.switchappTheme

const val switchDemoDocId = "AOYCis3xyZFxg2r4grRxzM"

enum class SwitchState {
    ON,
    OFF
}
@DesignDoc(id = switchDemoDocId)
interface SwitchDemo {
    @DesignComponent(node = "#MySwitch")
    fun InteractiveSwitch(
        @DesignVariant(property = "State") state: SwitchState,
        @Design(node = "#MySwitch") onTap: TapCallback
    )
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DesignSettings.enableLiveUpdates(this)
        setContent {
            switchappTheme {
                var currentSwitchState by remember { mutableStateOf(SwitchState.OFF) } // Default to OFF

                Column {
                    SwitchDemoDoc.InteractiveSwitch(
                        state = currentSwitchState,
                        onTap = {
                            currentSwitchState = if (currentSwitchState == SwitchState.OFF) SwitchState.ON else SwitchState.OFF
                        }
                    )
                    val message = "Switch is now ${currentSwitchState.name}"
                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()

                }
            }
        }
    }
}

