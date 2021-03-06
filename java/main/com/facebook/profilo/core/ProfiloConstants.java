/**
 * Copyright 2004-present, Facebook, Inc.
 *
 * <p>Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * <p>http://www.apache.org/licenses/LICENSE-2.0
 *
 * <p>Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.facebook.profilo.core;

public final class ProfiloConstants {

  public static final int NONE = 0;
  public static final int NO_CALLSITE = 0;
  public static final int NO_MATCH = 0;
  public static final int TRACING_DISABLED = -1;

  /* Keep in sync with AbortReason.h */
  public static final int ABORT_REASON_UNKNOWN = 1;
  public static final int ABORT_REASON_CONTROLLER_INITIATED = 2;
  public static final int ABORT_REASON_MISSED_EVENT = 3;
  public static final int ABORT_REASON_TIMEOUT = 4;
  public static final int ABORT_REASON_NEW_START = 5;

  // Things in the remote process can go wrong for the same reason as in the
  // main process. Thus, just mark if the reason is "remote" by using a single
  // bit
  public static final int REMOTE_PROCESS_BIT = 0x80000000;

  // Sampling rate config parameter for the call stacks provider.
  public static final String CPU_SAMPLING_RATE_CONFIG_PARAM =
      "provider.stack_trace.cpu_sampling_rate_ms";

  public static int packRemoteAbortReason(int abortReason) {
    return (abortReason | REMOTE_PROCESS_BIT);
  }

  public static int unpackRemoteAbortReason(int remoteAbortReason) {
    return (remoteAbortReason & ~REMOTE_PROCESS_BIT);
  }

  public static boolean isRemoteAbort(int abortReason) {
    return (abortReason & REMOTE_PROCESS_BIT) == REMOTE_PROCESS_BIT;
  }

  public static String abortReasonName(int abortReason) {
    switch (abortReason) {
      case ABORT_REASON_UNKNOWN:
        return "unknown";
      case ABORT_REASON_CONTROLLER_INITIATED:
        return "controller_init";
      case ABORT_REASON_MISSED_EVENT:
        return "missed_event";
      case ABORT_REASON_TIMEOUT:
        return "timeout";
      case ABORT_REASON_NEW_START:
        return "new_start";
    }
    return "UNKNOWN REASON " + abortReason;
  }

  public static final String TRACE_CONFIG_PARAM_TRACE_TIMEOUT_MS = "trace_config.trace_timeout_ms";
  public static final String TRACE_CONFIG_PARAM_LOGGER_PRIORITY = "trace_config.logger_priority";
  public static final int TRACE_CONFIG_PARAM_LOGGER_PRIORITY_DEFAULT = 5;
  public static final String TRACE_CONFIG_PARAM_POST_TRACE_EXTENSION_MSEC =
      "trace_config.post_trace_extension_ms";
  public static final int TRACE_CONFIG_PARAM_POST_TRACE_EXTENSION_MSEC_DEFAULT = 0;
  public static final String ATRACE_SINGLE_LIB_OPTIMIZATION_PARAM =
      "provider.atrace.single_lib_optimization";
}
