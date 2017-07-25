import React from 'react'
import { Route, IndexRoute } from 'react-router'

import {PageWithLeftBarLayout} from 'pages/PageWithLeftBarLayout'

import {SomePage} from 'pages/SomePage'
import {TermsList, EditTerm} from 'pages/terms'
import {FeedsList} from 'pages/feeds'
import {InfluencersList, UserCommentsList, Insights} from 'pages/comments'
import {Page_impressions} from 'pages/charts'
import {CreatePublication} from 'pages/publications'
import {MonitoringList, AddMonitoringTerm} from 'pages/monitoring'
import {ConversationsList, MessagesList} from 'pages/conversations/facebook/index'

export default (
    <Route name="app" component={PageWithLeftBarLayout} path="/">
        <IndexRoute component={SomePage}/>
        <Route path="terms" component={TermsList} />
        <Route path="terms/add" component={EditTerm} />
        <Route path="terms/:term" component={FeedsList} />
        <Route path="comments/topInfluencers" component={InfluencersList} />
        <Route path="comments/:client_id/:user_id" component={UserCommentsList} />
        <Route path="insights/comments/attijari" component={Insights} />
        <Route path="insights/charts/:metric" component={Page_impressions} />
        <Route path="publications/create" component={CreatePublication} />
        <Route path="monitoring/add" component={AddMonitoringTerm} />
        <Route path="monitoring/:term_ar/:term_fr" component={MonitoringList} />
        <Route path="conversations" component={ConversationsList} />
        <Route path="conversations/:conversationId" component={MessagesList} />
        {/*<Route path="conversation/twitter" component={TwitterConversationsList} />*/}

    </Route>
)
